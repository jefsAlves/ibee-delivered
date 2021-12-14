package com.example.food.model.services.impl;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.food.infra.kafka.integration.SendProduct;
import com.example.food.api.dto.ProductDTO;
import com.example.food.model.entities.Products;
import com.example.food.model.exceptions.IdNotFoudException;
import com.example.food.infra.mapper.impl.ProductsMapper;
import com.example.food.infra.repository.ProductRepository;
import com.example.food.model.services.ProductService;
import com.example.food.model.services.RestaurantService;
import com.example.food.model.util.MessageUtil;

@Service
public class ProductsServiceImpl implements ProductService {

	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private ProductsMapper mapper;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private SendProduct send;

	public Products search(Long productId) {
		Optional<Products> product = productRepository.findById(productId);
		return product.orElseThrow(() -> new IdNotFoudException(MessageUtil.ID_NOT_FOUND));
	}

	@Override
	public ProductDTO searchProduct(Long restaurantId, Long productId) {
		Optional<Products> product = productRepository.findById(restaurantId, productId);
		product.orElseThrow(() -> new IdNotFoudException(MessageUtil.ID_NOT_FOUND));
		return mapper.toDTOOptional(product);
	}

	public Products search(Long restaurantId, Long productId) {
		Optional<Products> product = productRepository.findById(restaurantId, productId);
		return product.orElseThrow(() -> new IdNotFoudException(MessageUtil.ID_NOT_FOUND));
	}

	@Override
	public List<ProductDTO> list(Long restaurantId) {
		var restaurant = restaurantService.search(restaurantId);
		List<Products> products = productRepository.findByRestaurant(restaurant);
		return mapper.toDTOList(products);
	}

	@Transactional
	@Override
	public ProductDTO createProduct(Long restaurantId, ProductDTO productsDTO) {
		var restaurant = restaurantService.search(restaurantId);
		var product = mapper.toEntity(productsDTO);
		product.setRestaurant(restaurant);
		productRepository.save(product);
		send.sendMessage(productsDTO);
		return mapper.toDTO(product);
	}

	@Transactional
	@Override
	public ProductDTO updateProduct(Long restaurantId, Long productId, ProductDTO productsDTO) {
		var product = search(restaurantId, productId);
		mapper.copyProperties(productsDTO, product);
		return mapper.toDTO(product);
	}

	@Transactional
	@Override
	public void deleteProduct() {
	}

}
