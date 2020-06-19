package com.ppe.productservice.product.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "product")
@Data
public class ProductEntity {
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;
	  private String name;
	  private String description;
	  private int price;
		
		  private int instock; 
		  private int inorder;
		  private int reorder;

		 
}
/*

INSERT INTO Product(id,name,price,unitsInStock,unitsOnOrder,reorderLevel,description)VALUES(1,'iPhone SE',399,50,0,10,'The powerful 4.7-inch iPhone SE (2nd Generation) features the A13 Bionic chip and 64GB of storage for incredible performance in apps, games, and photography.');
INSERT INTO Product(id,name,price,unitsInStock,unitsOnOrder,reorderLevel,description)VALUES(2,'iPhone 11',699,50,0,10,'The fastest chip ever in a smartphone and all‑day battery life let you do more and charge less.');
INSERT INTO Product(id,name,price,unitsInStock,unitsOnOrder,reorderLevel,description)VALUES(3,'AirPods',499,50,0,10,'AirPods deliver effortless, all-day audio on the go.');

*/