package com.ecommerce.delivery.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity 
@AllArgsConstructor
@NoArgsConstructor

public class Delivery {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

private long deliveryId;
	
	private long orderId;
	
	private String trackingNumber;
	
	@Column(length = 500)
	private String shippingaddress;
	
	
	private LocalDate estimatedDeliveryDate;
	
	private LocalDate actualDeliveryDate;
	
	private String status;

	public Delivery(long orderId, String trackingNumber, String shiippingaddress, LocalDate estimatedDeliveryDate,
			LocalDate actualDeliveryDate, String status) {
		super();
		this.orderId = orderId;
		this.trackingNumber = trackingNumber;
		this.shippingaddress = shiippingaddress;
		this.estimatedDeliveryDate = estimatedDeliveryDate;
		this.actualDeliveryDate = actualDeliveryDate;
		this.status = status;
	}
	
	
	}

	


