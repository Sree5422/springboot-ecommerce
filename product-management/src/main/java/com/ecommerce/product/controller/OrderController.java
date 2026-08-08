package com.ecommerce.product.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.product.dto.request.OrderCreateRequest;
import com.ecommerce.product.dto.request.OrderUpdateRequest;
import com.ecommerce.product.dto.response.OrderResponse;
import com.ecommerce.product.model.OrderItem;
import com.ecommerce.product.service.OrderService;


@RestController
@RequestMapping("/orders")
public class OrderController {

	private final OrderService orderService;
	
	public OrderController(OrderService orderService) {
		this.orderService=orderService;
	}
	
	@PostMapping
	
	public OrderResponse addOrder(@RequestBody OrderCreateRequest orderCreateRequest){
		return orderService.addOrder(orderCreateRequest);
	}
	
	@GetMapping
	
	public List<OrderResponse> getItems(){
		return orderService.getAllOrders();
	}
	
	@GetMapping("/{orderId}")
	public OrderResponse getOrderById(@PathVariable long orderId) {
		return orderService.getOrderById(orderId);
	}
	@PutMapping("/{orderId}")
	
	public OrderResponse updateOrder(@PathVariable long orderId, @RequestBody OrderUpdateRequest orderUpdateRequest) {
		return orderService.updateOrder(orderId,orderUpdateRequest);
	}
	
	@DeleteMapping("/{orderId}")
	
	public void deleteOrder(@PathVariable long orderId) {
		orderService.deleteOrder(orderId);
	}
	}
