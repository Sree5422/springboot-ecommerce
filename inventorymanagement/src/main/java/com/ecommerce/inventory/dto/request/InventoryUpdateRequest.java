package com.ecommerce.inventory.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryUpdateRequest {
	
	private int availableQuantity;
	
	private int reserveQuantity;
	
	private String warehouse;

}
