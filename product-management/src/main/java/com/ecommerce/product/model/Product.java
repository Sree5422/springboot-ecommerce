package com.ecommerce.product.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "products")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private long productId;
	
	private String productName;
	
	@Column(length = 1000)
	private String description;
	
	private String brand;
	
	private double price;
	
	private double rating;

	public Product(String productName, String description, String brand, double price, double rating) {
		super();
		this.productName = productName;
		this.description = description;
		this.brand = brand;
		this.price = price;
		this.rating = rating;
	}
	
	

}
