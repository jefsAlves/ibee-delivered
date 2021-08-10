package com.example.food.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.food.model.dto.ProductDTO;
import com.example.food.model.services.ProductsService;

@RestController
@RequestMapping(value = "restaurant/{restaurantId}")
public class ProductsController {

	@Autowired
	private ProductsService productsService;

	@GetMapping(value = "/product/{productId}")
	public ResponseEntity<ProductDTO> searchProduct(@PathVariable Long restaurantId, @PathVariable Long productId) {
		return new ResponseEntity<>(productsService.searchProduct(restaurantId, productId), HttpStatus.OK);
	}

	@GetMapping(value = "/product")
	public ResponseEntity<List<ProductDTO>> listProduct(@PathVariable Long restaurantId) {
		return new ResponseEntity<>(productsService.list(restaurantId), HttpStatus.OK);
	}

	@PutMapping(value = "/product/{productId}")
	public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long restaurantId, @PathVariable Long productId, @RequestBody ProductDTO productDTO) {
		return new ResponseEntity<>(productsService.updateProduct(restaurantId, productId, productDTO), HttpStatus.OK);
	}

	@PostMapping(value = "/product")
	public ResponseEntity<ProductDTO> createProduct(@PathVariable Long restaurantId, @RequestBody ProductDTO productsDTO) {
		return new ResponseEntity<>(productsService.createProduct(restaurantId, productsDTO), HttpStatus.CREATED);
	}

}
