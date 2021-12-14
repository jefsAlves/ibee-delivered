package com.example.food.model.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.food.api.dto.UserDTO;
import com.example.food.model.entities.User;
import com.example.food.infra.mapper.impl.UserMapper;
import com.example.food.infra.repository.UsersRepository;
import com.example.food.model.services.AssociateRestaurantUserService;
import com.example.food.model.services.RestaurantService;
import com.example.food.model.services.UserService;

@Service
public class AssociateRestaurantUserServiceImpl implements AssociateRestaurantUserService {

	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private UserService userService;

	@Autowired
	private UsersRepository userRepository;

	@Autowired
	private UserMapper mapper;

	@Override
	public List<UserDTO> listUsers(Long restaurantId) {
		List<User> user = userRepository.searchById(restaurantId);
		return mapper.toDTOList(user);
	}

	@Transactional
	@Override
	public void associateRestaurantToUser(Long restaurandId, Long userId) {
		var restaurant = restaurantService.search(restaurandId);
		var user = userService.search(userId);
		restaurant.addUser(user);
	}

	@Transactional
	@Override
	public void desassociateRestaurantToUser(Long restaurantId, Long userId) {
		var restaurant = restaurantService.search(restaurantId);
		var user = userService.search(userId);
		restaurant.removeUser(user);
	}

}
