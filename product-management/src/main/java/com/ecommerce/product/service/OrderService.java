package com.ecommerce.product.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.ecommerce.product.builder.OrderBuilder;
import com.ecommerce.product.builder.OrderItemBuilder;
import com.ecommerce.product.dto.request.OrderCreateRequest;
import com.ecommerce.product.dto.request.OrderUpdateRequest;
import com.ecommerce.product.dto.response.OrderItemResponse;
import com.ecommerce.product.dto.response.OrderResponse;
import com.ecommerce.product.dto.response.ProductResponse;
import com.ecommerce.product.dto.response.UserResponse;
import com.ecommerce.product.model.Order;
import com.ecommerce.product.model.OrderItem;
import com.ecommerce.product.model.Product;
import com.ecommerce.product.repository.OrderRepository;

@Service
public class OrderService {

	public final RestTemplate restTemplate;
	
	private final OrderRepository orderRepository;
	
	private final ProductService productService;
	
	public OrderService(OrderRepository orderRepository, RestTemplate restTemplate,ProductService productService) {
		this.orderRepository=orderRepository;
		this.restTemplate = restTemplate;
		this.productService = productService;
	}

	public OrderResponse addOrder(OrderCreateRequest orderCreateRequest) {

	    Order order = OrderBuilder.buildOrderByOrderCreateRequest(orderCreateRequest);

	    order.setStatus("ORDERED");
	    order.setOrderDate(LocalDateTime.now());

	    // Fetch price from ProductService
	    for (OrderItem item : order.getOrderItems()) {

	       // Product product = 
	    	ProductResponse productResponse = productService.getProductById(item.getProductId());
	    	

	        item.setPrice(productResponse.getPrice());
	    }

	    order.setTotalPrice(calculateTotalPrice(order.getOrderItems()));

	    Order savedOrder = orderRepository.save(order);

	    OrderResponse responseFromOrder =
	            OrderBuilder.buildOrderResponseFromOrder(savedOrder);

	    return responseFromOrder;
	}

//	public OrderResponse addOrder(OrderCreateRequest orderCreateRequest) {
//		Order order = OrderBuilder.buildOrderByOrderCreateRequest(orderCreateRequest);
//		order.setStatus("ORDERED");
//		order.setOrderDate(LocalDateTime.now());
//		order.setTotalPrice(calculateTotalPrice(order.getOrderItems()));
//		Order savedOrder = orderRepository.save(order);
//		OrderResponse responseFromOrder = OrderBuilder.buildOrderResponseFromOrder(savedOrder);
//		return responseFromOrder;
//	
//	}
	
	public double calculateTotalPrice(List<OrderItem> orderItems) {
		double total=0;
		
		for(OrderItem items:orderItems) {
			total+=items.getPrice()*items.getQuantity();
		}
		return total;
	}

	public List<OrderResponse> getAllOrders() {
		
		// TODO Auto-generated method stub
		return orderRepository.findAll()
				.stream()
				.map(OrderBuilder::buildOrderResponseFromOrder)
				.toList();
	}

	public OrderResponse getOrderById(long orderId) {
		Order order = orderRepository.findById(orderId).orElseThrow(()->new RuntimeException("Order ID not found"));
		OrderResponse orderResponseFromOrder = OrderBuilder.buildOrderResponseFromOrder(order);
		//   @Nullable
		UserResponse userResponse = restTemplate.getForObject("http://localhost:8081/users/"+orderResponseFromOrder.getUserId(),UserResponse.class);
		orderResponseFromOrder.setUserName(userResponse.getUserName());
		return orderResponseFromOrder;
	}

	public OrderResponse updateOrder(long orderId, OrderUpdateRequest orderUpdateRequest) {
		
		
		Order exOrder = orderRepository.findById(orderId).orElseThrow(()->new RuntimeException("Order ID not found to be updated"+orderId));
		exOrder.setStatus(orderUpdateRequest.getStatus());
		if (orderUpdateRequest.getOrderItems() != null) {

		List<OrderItem> updatedItems = orderUpdateRequest.getOrderItems().stream().map(OrderItemBuilder::buildOrderItemFromOrderItemUpdateRequest).toList();
		exOrder.getOrderItems().clear();
		exOrder.getOrderItems().addAll(updatedItems);
		OrderBuilder.linkOrderItems(exOrder);
		exOrder.setTotalPrice(calculateTotalPrice(exOrder.getOrderItems()));
		}
		Order savedOrder = orderRepository.save(exOrder);
		OrderResponse orderResponse = OrderBuilder.buildOrderResponseFromOrder(savedOrder);
		
		return orderResponse;
		
	}
	

	public void  deleteOrder(long orderId) {
		if(!orderRepository.existsById(orderId)) {
			throw new RuntimeException("Order Id not present" +orderId);
			
		}
		orderRepository.deleteById(orderId);
		// TODO Auto-generated method stub
	
	}
}
