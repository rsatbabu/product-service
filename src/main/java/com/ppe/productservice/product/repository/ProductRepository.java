package com.ppe.productservice.product.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ppe.productservice.product.entity.ProductEntity;


public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
	ProductEntity findById(long id);
	List<ProductEntity> findAll();
}