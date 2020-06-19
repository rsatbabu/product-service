package com.ppe.productservice.product.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ppe.productservice.product.entity.OrderEventEntity;



public interface OrderEventRepository extends CrudRepository<OrderEventEntity, Long> {

    List<OrderEventEntity> findByOrderconsumedFalse();
    List<OrderEventEntity> findByInventoryconsumedFalse();
    List<OrderEventEntity> findByEmailconsumedFalse();
}
