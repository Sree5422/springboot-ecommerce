package com.ecommerce.inventory.controller;

import java.util.List;
import java.util.function.LongBinaryOperator;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.inventory.dto.request.InventoryCreateRequest;
import com.ecommerce.inventory.dto.request.InventoryUpdateRequest;
import com.ecommerce.inventory.dto.response.InventoryResponse;
import com.ecommerce.inventory.service.InventoryService;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
	
	private final InventoryService inventoryService;
	
	public InventoryController(InventoryService inventoryService) {
		this.inventoryService=inventoryService;
	}
	
	@GetMapping
	public List<InventoryResponse> getAllInventories() {
		return inventoryService.getAllInventories();
	}
	
	@GetMapping("/{inventoryId}")
	public InventoryResponse getByInventoryId(@PathVariable long inventoryId) {
		return inventoryService.getInventoryById(inventoryId);
	}
	
	@PostMapping
	public InventoryResponse addInventory(@RequestBody InventoryCreateRequest inventoryCreateRequest) {
		return inventoryService.addInventory(inventoryCreateRequest);
	}
	
	@PatchMapping("/{inventoryId}")
	public InventoryResponse updateInventoryById	(@PathVariable long inventoryId, InventoryUpdateRequest inventoryUpdateRequest) {
		return inventoryService.updateInventoryById(inventoryId,inventoryUpdateRequest);
	}
	
	@DeleteMapping("/{inventoryId}")
	
	public  void  deleteInventoryById(@PathVariable long inventoryId) {
		 inventoryService.deleteInventoryById(inventoryId);
	}

}
