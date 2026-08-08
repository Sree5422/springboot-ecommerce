package com.ecommerce.product.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_items")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private long orderItemId;
	
	@ManyToOne
	@JoinColumn(name= "order_id")
	
	private Order order;
	
	private long productId;

	private int quantity;
		
	private double price;

	public OrderItem(long productId, int quantity, double price) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.price = price;
	}

	
	
	
	
	

}
