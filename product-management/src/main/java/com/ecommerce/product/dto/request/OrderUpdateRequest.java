package com.ecommerce.product.dto.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderUpdateRequest {
	
	private String status;

	private List<OrderItemUpdateRequest> orderItems;
}
