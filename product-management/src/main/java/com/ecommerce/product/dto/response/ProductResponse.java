package com.ecommerce.product.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ProductResponse {

private long productId;
	
	private String productName;

	private String description;
	
	private String brand;
	
	private double price;
	
	private double rating;
}
