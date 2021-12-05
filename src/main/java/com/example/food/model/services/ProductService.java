package com.example.food.model.services;

import java.util.List;

import com.example.food.api.dto.ProductDTO;

public interface ProductService {

	ProductDTO searchProduct(Long restaurantId, Long productId);

	List<ProductDTO> list(Long restaurantId);

	ProductDTO createProduct(Long restaurantId, ProductDTO productsDTO);

	ProductDTO updateProduct(Long restaurantId, Long productId, ProductDTO productsDTOS);

	void deleteProduct();

}
