package com.ecommerce.product.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class OrderResponse {
	
private long orderId;
	
	private long userId;
	
	private String userName;
	
	private double totalPrice;
	
	private String status;
	
	private LocalDateTime orderDate;
	
	private List<OrderItemResponse> orderItems;

}
