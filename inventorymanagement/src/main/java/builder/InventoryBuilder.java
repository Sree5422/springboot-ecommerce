package builder;

import com.ecommerce.inventory.dto.request.InventoryCreateRequest;
import com.ecommerce.inventory.dto.request.InventoryUpdateRequest;
import com.ecommerce.inventory.dto.response.InventoryResponse;
import com.ecommerce.inventory.model.Inventory;

public class InventoryBuilder {
	
	public static Inventory buildInventoryFromInventoryCreateRequest(InventoryCreateRequest inventoryCreateRequest) {
		return Inventory.builder().productId(inventoryCreateRequest.getProductId())
		.availableQuantity(inventoryCreateRequest.getAvailableQuantity())
		.reserveQuantity(inventoryCreateRequest.getReserveQuantity())
		.warehouse(inventoryCreateRequest.getWarehouse())
		.build();
	}
	
	public static InventoryResponse buildInventoryResponseFromInventory(Inventory inventory) {
		 return InventoryResponse.builder().availableQuantity(inventory.getAvailableQuantity())
		.productId(inventory.getProductId())
		.inventoryId(inventory.getInventoryId())
		.reserveQuantity(inventory.getReserveQuantity())
		.warehouse(inventory.getWarehouse())
		.build();
	}

	public static Inventory buildInventoryFromInventoryUpdateRequest(Inventory exInventory, InventoryUpdateRequest inventoryUpdateRequest) {
		return Inventory.builder().availableQuantity(inventoryUpdateRequest.getAvailableQuantity())
				.reserveQuantity(inventoryUpdateRequest.getReserveQuantity())
				.warehouse(inventoryUpdateRequest.getWarehouse())
				.productId(exInventory.getProductId())
				.inventoryId(exInventory.getInventoryId())
				.build();
	}

}
