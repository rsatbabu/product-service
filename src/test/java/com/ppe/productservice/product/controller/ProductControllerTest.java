package com.ppe.productservice.product.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ppe.productservice.product.entity.OrderEventEntity;
import com.ppe.productservice.product.entity.ProductEntity;
import com.ppe.productservice.product.repository.OrderEventRepository;
import com.ppe.productservice.product.repository.ProductMongoRepository;
import com.ppe.productservice.product.repository.ProductRepository;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private OrderEventRepository orderEventRepository;

	@MockBean
	private ProductRepository productRepository;

	@MockBean
	private ProductMongoRepository productMongoRepository;

	@MockBean
	private ObjectMapper objectMapper;
	
	@Test
	public void processOrderEventsForProduct() throws Exception {
		
		List<OrderEventEntity> orderEventEntities = getOrderEventEntities();
		when(orderEventRepository.findByInventoryconsumedFalse()).thenReturn(orderEventEntities);
		when(productRepository.findById(1L)).thenReturn(getProductEntity());
		when(productRepository.findAll()).thenReturn(getProductEntities());
		
		this.mockMvc.perform(post("/processOrderEventsForProduct")).andExpect(status().isOk());
	}
	
	private List<OrderEventEntity> getOrderEventEntities() {
		List<OrderEventEntity> orderEventEntities = new ArrayList<>();
		orderEventEntities.add(getOrderEventEntity());
		return orderEventEntities;
		
	}
	
	private OrderEventEntity getOrderEventEntity() {
		OrderEventEntity orderEventEntity = new OrderEventEntity();
		orderEventEntity.setCustomerId("test");
		orderEventEntity.setId(1L);
		orderEventEntity.setOrderconsumed(false);
		orderEventEntity.setProductId(1);
		orderEventEntity.setOrderId("id");
		orderEventEntity.setQuantity(5);
		return orderEventEntity;
	}
	
	private ProductEntity getProductEntity() {
		ProductEntity productEntity = new ProductEntity();
		productEntity.setId(1L);
		productEntity.setName("product");
		productEntity.setPrice(10);
		productEntity.setInstock(100);
		productEntity.setInorder(0);
		productEntity.setReorder(5);
		return productEntity;
	}
	private List<ProductEntity> getProductEntities(){
		List<ProductEntity> products = new ArrayList<>();
		products.add(getProductEntity());
		return products;
	}
	
	
}
