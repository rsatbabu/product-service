package com.ppe.productservice.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ppe.productservice.product.entity.OrderEventEntity;
import com.ppe.productservice.product.entity.ProductEntity;
import com.ppe.productservice.product.entity.ProductMongoEntity;
import com.ppe.productservice.product.model.Product;
import com.ppe.productservice.product.repository.OrderEventRepository;
import com.ppe.productservice.product.repository.ProductMongoRepository;
import com.ppe.productservice.product.repository.ProductRepository;

@RestController
public class ProductController {

	@Autowired
	private OrderEventRepository orderEventRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductMongoRepository productMongoRepository;

	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * This API consumes the order events from the stream
	 * and updates the product table and the product document table
	 * 
	 */
	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping("/processOrderEventsForProduct")
	public void processOrderEventsForProduct() {
		// Simulate a Consumer reading from MQ
		List<OrderEventEntity> orderEventEntities = orderEventRepository.findByInventoryconsumedFalse();

		// Update the stock
		orderEventEntities.stream().forEach(orderEventEntity -> {
			ProductEntity productEntity = productRepository.findById(orderEventEntity.getProductId());
			productEntity.setInstock(productEntity.getInstock() - orderEventEntity.getQuantity());
			productRepository.save(productEntity);
			// Update the Read Model for the view - CQRS
			updateProductDocumentRecord(productEntity);
			orderEventEntity.setInventoryconsumed(true);

		});

		
		List<ProductEntity> products = productRepository.findAll();
		products.forEach(productEntity -> System.out
				.println("Product Id " + productEntity.getId() + "Quantity " + productEntity.getInstock()));

		// -- Simulate a consumer commiting the record in MQ
		orderEventRepository.saveAll(orderEventEntities);
	}

	private void updateProductDocumentRecord(ProductEntity productEntity) {
		Product product = new Product();
		new Product(productEntity.getId(),productEntity.getName(),productEntity.getDescription(),
				productEntity.getPrice(),productEntity.getInstock());

		String detail="";
		try {
			detail = objectMapper.writeValueAsString(product);

		} catch (JsonProcessingException e) {
			throw new HttpClientErrorException(HttpStatus.SERVICE_UNAVAILABLE);
		}
		ProductMongoEntity productMongoEntity = new ProductMongoEntity(product.getId(), detail);
		productMongoRepository.save(productMongoEntity);
	}

}
