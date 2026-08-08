package com.ecommerce.product.builder;

import com.ecommerce.product.dto.request.ProductCreateRequest;
import com.ecommerce.product.dto.request.ProductUpdateRequest;
import com.ecommerce.product.dto.response.ProductResponse;
import com.ecommerce.product.model.Product;

import lombok.Builder;

@Builder

public class ProductBuilder {
	
	public static Product buildProductFromProductCreateRequest(ProductCreateRequest productCreateRequest) {
		return Product.builder().productName(productCreateRequest.getProductName())
				.brand(productCreateRequest.getBrand())
				.description(productCreateRequest.getDescription())
				.price(productCreateRequest.getPrice())
				.rating(productCreateRequest.getRating())
				.build();
	}
	
	
	public static ProductResponse buildProductResponseFromProduct(Product product) {
		return ProductResponse.builder().productId(product.getProductId())
				.productName(product.getProductName())
				.brand(product.getBrand())
				.price(product.getPrice())
				.description(product.getDescription())
				.rating(product.getRating())
				.build();
	}
	
	public static Product buildProductFromProductUpdateRequest(Product existingProduct,ProductUpdateRequest productUpdateRequest) {
		return Product.builder().productId(existingProduct.getProductId())
				.productName(productUpdateRequest.getProductName())
				.brand(productUpdateRequest.getBrand())
				.description(productUpdateRequest.getDescription())
				.price(productUpdateRequest.getPrice())
				.rating(productUpdateRequest.getRating())
				.build();
				
				
	}

}
