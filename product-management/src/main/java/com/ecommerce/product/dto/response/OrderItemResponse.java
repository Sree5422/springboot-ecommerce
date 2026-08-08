package com.ecommerce.product.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class OrderItemResponse {

private long orderItemId;
	
	private long orderId;
	
	private long productId;
	
	private int quantity;
	
	private double price;
}
