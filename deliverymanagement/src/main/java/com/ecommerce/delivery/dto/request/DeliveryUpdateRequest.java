package com.ecommerce.delivery.dto.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryUpdateRequest {
	
private String shippingAddress;
	
	private LocalDate estimatedDeliveryDate;
	
	private LocalDate actualDeliveryDate;
	
	private String status;

}
