package com.ecommerce.delivery.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.delivery.dto.request.DeliveryCreateRequest;
import com.ecommerce.delivery.dto.request.DeliveryUpdateRequest;
import com.ecommerce.delivery.dto.response.DeliveryResponse;
import com.ecommerce.delivery.service.DeliveryService;

@RestController
@RequestMapping("/delivery")

public class DeliverController {
	
	private final DeliveryService deliveryService;
	
	public DeliverController(DeliveryService deliveryService) {
		this.deliveryService=deliveryService;
	}
	
	@GetMapping
	public List<DeliveryResponse> getAllDeliveries(){
		return deliveryService.getAllDeliveries();
	}
	
	@GetMapping("/{deliveryId}")
	public DeliveryResponse getByDeliveyId(long deliveryId) {
		return deliveryService.getByDeliveryId(deliveryId);
	}
	
	@PostMapping
	public DeliveryResponse assignDelivery(@RequestBody DeliveryCreateRequest deliveryCreateRequest) {
		return deliveryService.assignDelivery(deliveryCreateRequest);
}
	@PatchMapping
	public DeliveryResponse updateDelivery(@RequestBody DeliveryUpdateRequest deliveryUpdateRequest) {
		return deliveryService.updateDelivery(deliveryUpdateRequest);
	}
}
