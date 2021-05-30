package com.example.food.model.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.food.model.entities.Restaurant;
import com.example.food.model.exceptions.BusinessException;
import com.example.food.model.repository.RestaurantRepository;
import com.example.food.model.services.RestaurantService;
import com.example.food.model.util.MessageUtil;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Override
	public Restaurant searchRestaurant(Long id) {
		Optional<Restaurant> findId = restaurantRepository.findById(id);
		return findId.orElseThrow(() -> new BusinessException(MessageUtil.ID_NOT_FOUND));
	}

	@Override
	public Restaurant searchRestaurantByName(String name) {
		Restaurant restaurantName = restaurantRepository.searchNameRestaurant(name);
		if (restaurantName == null) {
			throw new BusinessException(MessageUtil.RESTAURANT_NOT_EXIST);
		}
		return restaurantName;
	}

	@Override
	public List<Restaurant> listsRestaurants(Restaurant restaurant) {
		List<Restaurant> findRestaurant = restaurantRepository.findAll();
		return findRestaurant;
	}

	@Transactional
	@Override
	public Restaurant createRestaurant(Restaurant restaurant) {
		return restaurantRepository.save(restaurant);
	}

	@Transactional
	@Override
	public Restaurant updateRestaurant(Long id, Restaurant restaurant) {
		return validAlreadyRestaurantExist(id, restaurant);
	}

	private Restaurant validAlreadyRestaurantExist(Long id, Restaurant restaurant) {
		Optional<Restaurant> restaurantBase = restaurantRepository.findById(id);
		if (restaurantBase.isPresent()) {
			BeanUtils.copyProperties(restaurant, restaurantBase.get(), "id");
			return restaurantRepository.save(restaurantBase.get());
		}
		throw new BusinessException(MessageUtil.ID_NOT_FOUND);
	}

	@Transactional
	@Override
	public void deleteRestaurant(Long restaurantId) {
		restaurantRepository.deleteById(restaurantId);
	}

}
