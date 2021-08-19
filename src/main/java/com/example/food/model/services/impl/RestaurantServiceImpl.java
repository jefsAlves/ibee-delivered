package com.example.food.model.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.food.config.integration.SendRestaurant;
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

	@Autowired
	private SendRestaurant integration;

	@Override
	public Restaurant search(Long restaurantId) {
		Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
		return restaurant.orElseThrow(() -> new IdNotFoudException(MessageUtil.ID_NOT_FOUND));
	}

	@Override
	public RestaurantDTO searchRestaurant(Long id) {
		Optional<Restaurant> restaurant = restaurantRepository.findById(id);
		restaurant.orElseThrow(() -> new IdNotFoudException(MessageUtil.ID_NOT_FOUND));
		return mapper.toDTO(restaurant);
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
	public List<Restaurant> listRestaurants(Restaurant restaurant) {
		List<Restaurant> findRestaurant = restaurantRepository.findAll();
		return findRestaurant;
	}

	@Transactional
	@Override
	public RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO) {
		var restaurant = mapper.toEntity(restaurantDTO);
		validationRestaurant.verifyRestaurantExist(restaurant.getName());
		restaurantRepository.save(restaurant);
		integration.sendMessage(restaurantDTO);
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

	@Override
	@Transactional
	public void activeRestaurant(Long activeId) {
		var restaurant = search(activeId);
		restaurant.active();
	}

	@Override
	@Transactional
	public void desactiveRestaurant(Long desactiveId) {
		var restaurant = search(desactiveId);
		restaurant.desactive();
	}

	@Transactional
	@Override
	public void activeAll(List<Long> restaurantIds) {
		restaurantIds.forEach(this::activeRestaurant);
	}
	
	@Transactional
	@Override
	public void desactiveRestaurantAll(List<Long> restaurantIds) {
		restaurantIds.forEach(this::desactiveRestaurant);
	}

	@Transactional
	@Override
	public void openRestaurant(Long restaurantId) {
		var restaurant = search(restaurantId);
		restaurant.open(restaurantId);
	}

	@Transactional
	@Override
	public void closeRestaurant(Long restaurantId) {
		var restaurant = search(restaurantId);
		restaurant.close(restaurantId);
	}

}
