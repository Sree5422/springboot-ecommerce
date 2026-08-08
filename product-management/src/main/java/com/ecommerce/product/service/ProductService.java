package com.ecommerce.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.product.builder.ProductBuilder;
import com.ecommerce.product.dto.request.ProductCreateRequest;
import com.ecommerce.product.dto.response.ProductResponse;
import com.ecommerce.product.model.Product;
import com.ecommerce.product.repository.ProductRepository;

@Service
public class ProductService {

	private final ProductRepository productRepository;
	
	public ProductService(ProductRepository pro) {
		this.productRepository = pro;
	}
	
	
	
	public List<ProductResponse> getAllProducts() {

		List<ProductResponse> listedProducts = productRepository.findAll().stream().map(ProductBuilder::buildProductResponseFromProduct).toList();
		return listedProducts;
	}



	public ProductResponse addProduct(ProductCreateRequest productCreateRequest) {
		Product productRequest = ProductBuilder.buildProductFromProductCreateRequest(productCreateRequest);
		Product savedProduct = productRepository.save(productRequest);
		ProductResponse Response = ProductBuilder.buildProductResponseFromProduct(savedProduct);
		
		return Response;
	}



	public ProductResponse getProductById(long productId) {
		Product product = productRepository.findById(productId).orElseThrow(()->new RuntimeException("Product ID not found"));
		ProductResponse responseFromProduct = ProductBuilder.buildProductResponseFromProduct(product);
		// TODO Auto-generated method stub
		return responseFromProduct;
	}



	public ProductResponse updateProductById(long productId, ProductCreateRequest createRequest) {
		Product throw1 = productRepository.findById(productId).orElseThrow(()->new RuntimeException("Product ID not found"));
		ProductResponse response = ProductBuilder.buildProductResponseFromProduct(throw1);
		return response;
	}



	public void  deleteProduct(long productId) {
			if(!productRepository.existsById(productId)) {
				throw new RuntimeException("Product Id not Present");
			}
			 productRepository.deleteById(productId);
	}

}
