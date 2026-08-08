package com.ecommerce.product.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateRequest {
	
private String productName;
	
	private String description;
	
	private String brand;
	
	private double price;
	
	private double rating;

}
