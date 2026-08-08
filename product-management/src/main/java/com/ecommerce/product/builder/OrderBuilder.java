package com.ecommerce.product.builder;

import java.util.List;

import com.ecommerce.product.dto.request.OrderCreateRequest;
import com.ecommerce.product.dto.response.OrderItemResponse;
import com.ecommerce.product.dto.response.OrderResponse;
import com.ecommerce.product.model.Order;
import com.ecommerce.product.model.OrderItem;

public class OrderBuilder {

	public static Order buildOrderByOrderCreateRequest(OrderCreateRequest orderCreateRequest) {
		Order order = Order.builder()
				.orderItems(orderCreateRequest.getOrderItems().stream().map(OrderItemBuilder::buildOrderItemFromOrderItemCreateRequest).toList())
				.userId(orderCreateRequest.getUserId())
				.build();
		linkOrderItems(order);
		return order;
	}
	
	public static void linkOrderItems(Order order) {
		order.getOrderItems().forEach(item->item.setOrder(order));
	}
	
	public static OrderResponse buildOrderResponseFromOrder(Order order) {
		List<OrderItemResponse> orderItems = order.getOrderItems().stream().map(OrderItemBuilder::buildOrderItemResponseFromOrderItem).toList();
		return OrderResponse.builder()
				.orderId(order.getOrderId())
				.orderDate(order.getOrderDate())
				.totalPrice(order.getTotalPrice())
				.status(order.getStatus())
				.userId(order.getUserId())
				.orderItems(orderItems)
				.build();
	}
}
