package com.example.food.model.mapper;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.food.model.dto.ProductDTO;
import com.example.food.model.entities.Products;

@Component
public class ProductsMapper {

	@Autowired
	private ModelMapper modelMapper;

	public ProductDTO toDTO(Products products) {
		return modelMapper.map(products, ProductDTO.class);
	}

	public Products toEntity(ProductDTO productsDTO) {
		return modelMapper.map(productsDTO, Products.class);
	}

	public List<ProductDTO> toDTOList(List<Products> products) {
		return modelMapper.map(products, new TypeToken<List<Products>>() {}.getType());
	}

	public List<Products> toEntityList(List<ProductDTO> productsDTO) {
		return modelMapper.map(productsDTO, new TypeToken<List<ProductsMapper>>() {}.getType());
	}

	public ProductDTO toDTOOptional(Optional<Products> products) {
		ProductDTO productsDTO = new ProductDTO();
		productsDTO.setId(products.get().getId());
		productsDTO.setPrice(products.get().getPrice());
		productsDTO.setDescription(products.get().getDescription());
		productsDTO.setActive(products.get().getActive());
		return productsDTO;
	}
	
	public void copyProperties(ProductDTO productsDTO, Products products) {
		BeanUtils.copyProperties(productsDTO, products, "id");
	}

}
