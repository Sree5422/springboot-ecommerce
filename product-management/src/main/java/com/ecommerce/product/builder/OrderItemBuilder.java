package com.ecommerce.product.builder;

import com.ecommerce.product.dto.request.OrderItemCreateRequest;
import com.ecommerce.product.dto.request.OrderItemUpdateRequest;
import com.ecommerce.product.dto.response.OrderItemResponse;
import com.ecommerce.product.model.OrderItem;

public class OrderItemBuilder {

	public static OrderItem buildOrderItemFromOrderItemCreateRequest(OrderItemCreateRequest orderItemCreateRequest) {
		return OrderItem.builder().productId(orderItemCreateRequest.getProductId())
		.quantity(orderItemCreateRequest.getQuantity())
		.build();
	}
	
	public static OrderItemResponse buildOrderItemResponseFromOrderItem(OrderItem orderItem) {
		return OrderItemResponse.builder()
		.orderItemId(orderItem.getOrderItemId())
		.orderId(orderItem.getOrder().getOrderId())
		.price(orderItem.getPrice())
		.productId(orderItem.getProductId())
		.quantity(orderItem.getQuantity())
		.build();
	}
	
	public static OrderItem buildOrderItemFromOrderItemUpdateRequest(OrderItemUpdateRequest itemUpdateRequest) {
		return OrderItem.builder()
		.productId(itemUpdateRequest.getProductId())
		.orderItemId(itemUpdateRequest.getOrderItemId())
		.quantity(itemUpdateRequest.getQuantity())
		.build();
		}
}
