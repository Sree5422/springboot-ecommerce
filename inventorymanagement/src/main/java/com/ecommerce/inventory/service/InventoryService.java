package com.ecommerce.inventory.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.ecommerce.inventory.dto.request.InventoryCreateRequest;
import com.ecommerce.inventory.dto.request.InventoryUpdateRequest;
import com.ecommerce.inventory.dto.response.InventoryResponse;
import com.ecommerce.inventory.model.Inventory;
import com.ecommerce.inventory.repository.InventoryRepository;

import builder.InventoryBuilder;

@Service
public class InventoryService {
	
	private final InventoryRepository inventoryRepository;
	
	public InventoryService(InventoryRepository inventoryRepository) {
		this.inventoryRepository = inventoryRepository;
	}

	public List<InventoryResponse> getAllInventories() {
		List<InventoryResponse> list = inventoryRepository.findAll().stream().map(InventoryBuilder::buildInventoryResponseFromInventory).toList();
		// TODO Auto-generated method stub
		return list;
	}
	
	

	public InventoryResponse addInventory(InventoryCreateRequest inventoryCreateRequest) {
		Inventory inventory = InventoryBuilder.buildInventoryFromInventoryCreateRequest(inventoryCreateRequest);
		Inventory saved = inventoryRepository.save(inventory);
		InventoryResponse inventoryResponse= InventoryBuilder.buildInventoryResponseFromInventory(saved);
		// TODO Auto-generated method stub
		return inventoryResponse;
	}

	public  InventoryResponse updateInventoryById(long inventoryId,InventoryUpdateRequest inventoryUpdateRequest) {
		
		// TODO Auto-generated method stub
		Inventory exInventory = inventoryRepository.findById(inventoryId).orElseThrow(()-> new RuntimeException("Inventory Id not found"));
		Inventory InventoryUpdateRequest = InventoryBuilder.buildInventoryFromInventoryUpdateRequest(exInventory, inventoryUpdateRequest);
		Inventory saved = inventoryRepository.save(InventoryUpdateRequest);
		return InventoryBuilder.buildInventoryResponseFromInventory(saved);
		
	}

	public InventoryResponse getInventoryById(long inventoryId) {
		  Inventory inventory = inventoryRepository.findById(inventoryId).orElseThrow(()->new RuntimeException("Inventory Id not found"));
		  	InventoryResponse inventoryResponse = InventoryBuilder.buildInventoryResponseFromInventory(inventory);
		// TODO Auto-generated method stub
		return inventoryResponse;
	}

	public void deleteInventoryById(long inventoryId) {
		if(!inventoryRepository.existsById(inventoryId)) {
			throw new RuntimeException("Inventory Id not found");
		}
		inventoryRepository.deleteById(inventoryId);
		// TODO Auto-generated method stub
	  

}
	
	

}
