package com.example.food.model.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.food.model.dto.RestaurantDTO;
import com.example.food.model.entities.Restaurant;
import com.example.food.model.exceptions.IdNotFoudException;
import com.example.food.model.exceptions.RestaurantException;
import com.example.food.model.mapper.RestaurantMapper;
import com.example.food.model.repository.RestaurantRepository;
import com.example.food.model.services.RestaurantService;
import com.example.food.model.util.MessageUtil;
import com.example.food.model.util.ValidationRestaurant;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private ValidationRestaurant validationRestaurant;

	@Autowired
	private RestaurantMapper mapper;

	@Override
	public Restaurant searchRestaurant(Long id) {
		Optional<Restaurant> findId = restaurantRepository.findById(id);
		return findId.orElseThrow(() -> new IdNotFoudException(MessageUtil.ID_NOT_FOUND));
	}

	@Override
	public RestaurantDTO searchRestaurantByName(String name) {
		Restaurant restaurant = restaurantRepository.findByName(name);
		if (restaurant == null) {
			throw new RestaurantException(MessageUtil.RESTAURANT_NOT_EXIST);
		}
		return mapper.toDTO(restaurant);
	}

	@Override
	public List<Restaurant> listsRestaurants(Restaurant restaurant) {
		List<Restaurant> findRestaurant = restaurantRepository.findAll();
		return findRestaurant;
	}

	@Transactional
	@Override
	public RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO) {
		var restaurant = mapper.toEntity(restaurantDTO);
		validationRestaurant.verifyRestaurantExist(restaurant.getName());
		restaurantRepository.save(restaurant);
		return mapper.toDTO(restaurant);
	}

	@Transactional
	@Override
	public RestaurantDTO updateRestaurant(Long id, RestaurantDTO restaurantDTO) {
		Restaurant restaurantBase = validationRestaurant.verifyRestaurantExist(id);
		restaurantBase = mapper.toEntity(restaurantDTO);
		mapper.copyProperties(restaurantDTO, restaurantBase);
		restaurantBase = restaurantRepository.save(restaurantBase);
		return mapper.toDTO(restaurantBase);
	}

	@Transactional
	@Override
	public void deleteRestaurant(Long restaurantId) {
		try {
			restaurantRepository.deleteById(restaurantId);
		} 
		catch (EmptyResultDataAccessException e) {
			throw new IdNotFoudException(MessageUtil.ID_NOT_FOUND);
		}
	}

}
