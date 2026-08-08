package com.ecommerce.delivery.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.delivery.builder.DeliveryBuilder;
import com.ecommerce.delivery.dto.request.DeliveryCreateRequest;
import com.ecommerce.delivery.dto.request.DeliveryUpdateRequest;
import com.ecommerce.delivery.dto.response.DeliveryResponse;
import com.ecommerce.delivery.model.Delivery;
import com.ecommerce.delivery.repository.DeliveryRepository;

@Service
public class DeliveryService {
	
	private final DeliveryRepository deliveryRepository;
	
	public DeliveryService(DeliveryRepository deliveryRepository) {
		this.deliveryRepository=deliveryRepository;
	}

	public List<DeliveryResponse> getAllDeliveries() {
		
		  List<DeliveryResponse> list = deliveryRepository.findAll().stream().map(DeliveryBuilder::buildDeliveryResponseFromDelivery).toList();
			return list;
			
	}

	public DeliveryResponse getByDeliveryId(long deliveryId) {
		
		 Delivery delivery = deliveryRepository.findById(deliveryId).orElseThrow(()-> new RuntimeException("Delivery Id not found"));
		 DeliveryResponse deliveryResponseFromDelivery = DeliveryBuilder.buildDeliveryResponseFromDelivery(delivery);
		return deliveryResponseFromDelivery;
	}

	public DeliveryResponse assignDelivery(DeliveryCreateRequest deliveryCreateRequest) {
		Delivery delivery = DeliveryBuilder.buildDeliveryFromDeliveryCreateRequest(deliveryCreateRequest);
		
		Delivery savedDelivery = deliveryRepository.save(delivery);
		DeliveryResponse deliveryResponseFromDelivery = DeliveryBuilder.buildDeliveryResponseFromDelivery(savedDelivery);
		return deliveryResponseFromDelivery;
	}

	public DeliveryResponse updateDelivery(DeliveryUpdateRequest deliveryUpdateRequest) {
		// TODO Auto-generated method stub
		Delivery delivery = DeliveryBuilder.buildDeliveryFromDeliveryUpdateRequest(deliveryUpdateRequest);
		Delivery savedDelivery = deliveryRepository.save(delivery);
		DeliveryResponse deliveryResponse = DeliveryBuilder.buildDeliveryResponseFromDelivery(savedDelivery);
		return deliveryResponse;
	}

}
