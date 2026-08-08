package com.ecommerce.inventory.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryResponse {


	private long inventoryId;
	
	private long productId;
	
	private int availableQuantity;
	
	private int reserveQuantity;
	
	private String warehouse;
}
