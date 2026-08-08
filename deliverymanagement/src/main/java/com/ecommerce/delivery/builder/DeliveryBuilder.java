package com.ecommerce.delivery.builder;

import com.ecommerce.delivery.dto.request.DeliveryCreateRequest;
import com.ecommerce.delivery.dto.request.DeliveryUpdateRequest;
import com.ecommerce.delivery.dto.response.DeliveryResponse;
import com.ecommerce.delivery.model.Delivery;

public class DeliveryBuilder {
	
	public static Delivery buildDeliveryFromDeliveryCreateRequest(DeliveryCreateRequest deliveryCreateRequest) {
		return Delivery.builder().orderId(deliveryCreateRequest.getOrderId())
		.trackingNumber(deliveryCreateRequest.getTrackingNumber())
		.shippingaddress(deliveryCreateRequest.getShippingAddress())
		.estimatedDeliveryDate(deliveryCreateRequest.getEstimatedDeliveryDate())
		.status(deliveryCreateRequest.getStatus()).build();
	}
	
	public static DeliveryResponse buildDeliveryResponseFromDelivery(Delivery delivery) {
		 return   DeliveryResponse.builder().deliveryId(delivery.getDeliveryId())
		   .orderId(delivery.getOrderId())
		   .estimatedDeliveryDate(delivery.getEstimatedDeliveryDate())
		   .actualDeliveryDate(delivery.getActualDeliveryDate())
		   .shippingAddress(delivery.getShippingaddress())
		   .status(delivery.getStatus())
		   .trackingNumber(delivery.getTrackingNumber()).build();
		   
	}
	
	public static Delivery buildDeliveryFromDeliveryUpdateRequest(DeliveryUpdateRequest deliveryUpdateRequest) {
		return Delivery.builder().status(deliveryUpdateRequest.getStatus())
				.shippingaddress(deliveryUpdateRequest.getShippingAddress())
				.actualDeliveryDate(deliveryUpdateRequest.getActualDeliveryDate())
				.estimatedDeliveryDate(deliveryUpdateRequest.getEstimatedDeliveryDate())
				.build();
	}
	

}
