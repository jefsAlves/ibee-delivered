package com.example.food.model.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.food.model.entities.Restaurant;
import com.example.food.model.exceptions.IdNotFoudException;
import com.example.food.model.exceptions.RestaurantException;
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

	@Override
	public Restaurant searchRestaurant(Long id) {
		Optional<Restaurant> findId = restaurantRepository.findById(id);
		return findId.orElseThrow(() -> new IdNotFoudException(MessageUtil.ID_NOT_FOUND));
	}

	@Override
	public Restaurant searchRestaurantByName(String name) {
		Restaurant restaurantName = restaurantRepository.searchNameRestaurant(name);
		if (restaurantName == null) {
			throw new RestaurantException(MessageUtil.RESTAURANT_NOT_EXIST);
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
		validationRestaurant.verifyRestaurantExist(restaurant.getName());
		return restaurantRepository.save(restaurant);
	}

	@Transactional
	@Override
	public Restaurant updateRestaurant(Long id, Restaurant restaurant) {
		Restaurant restaurantValid = validationRestaurant.verifyRestaurantExist(id);
		BeanUtils.copyProperties(restaurant, restaurantValid, "id", "createDate", "updateDate", "address", "payments",
				"products");
		return restaurantRepository.save(restaurantValid);
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
