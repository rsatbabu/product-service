package com.ppe.productservice.product.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ppe.productservice.product.entity.ProductMongoEntity;




  public interface ProductMongoRepository extends CrudRepository<ProductMongoEntity,Long> {
	  	ProductMongoEntity findById(long id);
		List<ProductMongoEntity> findAll();
  }
 