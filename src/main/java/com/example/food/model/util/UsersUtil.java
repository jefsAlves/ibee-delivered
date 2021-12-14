package com.example.food.model.util;

import java.util.Optional;

import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.food.model.entities.User;
import com.example.food.model.exceptions.IdNotFoudException;
import com.example.food.model.exceptions.RestaurantException;
import com.example.food.infra.repository.UsersRepository;

@Component
public class UsersUtil {

	@Autowired
	private UsersRepository usersRepository;

	public User verifyUserExist(Long userId) {
		Optional<User> users = usersRepository.findById(userId);
		return users.orElseThrow(() -> new IdNotFoudException(MessageUtil.ID_NOT_FOUND));
	}

	public boolean verifyUserExist(String user) {
		var users = usersRepository.findByUser(user);
		if (users != null) {
			throw new RestaurantException(MessageUtil.EMAIL_ALREADY_EXIST);
		}
		return true;
	}

}
