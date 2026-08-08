package com.ecommerce.product.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.product.dto.request.ProductCreateRequest;
import com.ecommerce.product.dto.response.ProductResponse;
import com.ecommerce.product.service.ProductService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	private final ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@PostMapping
	
	public ProductResponse addProduct(@RequestBody ProductCreateRequest productCreateRequest) {
		return productService.addProduct(productCreateRequest);
	}
	
	@GetMapping
	
	public List<ProductResponse> retrieveAllProducts() {
		return productService.getAllProducts();
	}
	
	@GetMapping ("/{productId}")
	
	public ProductResponse getProductById(@PathVariable long productId) {
		return productService.getProductById(productId);
	}
	
	@PatchMapping("/{productId}")
	
	public ProductResponse updateProduct(@PathVariable long productId, @RequestBody ProductCreateRequest createRequest) {
		return productService.updateProductById(productId,createRequest);
	}
	
	@DeleteMapping("/{productId}")
	
	public void deleteProductById(@PathVariable long productId) {
		 productService.deleteProduct(productId);
	}

}
