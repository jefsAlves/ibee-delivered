package com.example.food.model.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.food.api.dto.UserDTO;
import com.example.food.api.dto.UserPasswordDTO;
import com.example.food.model.entities.User;
import com.example.food.model.exceptions.BusinessException;
import com.example.food.model.exceptions.CannotDeleteException;
import com.example.food.model.exceptions.IdNotFoudException;
import com.example.food.infra.mapper.impl.UserMapper;
import com.example.food.infra.repository.UsersRepository;
import com.example.food.model.services.UserService;
import com.example.food.model.util.MessageUtil;
import com.example.food.model.util.UsersUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UsersRepository userRepository;

	@Autowired
	private UserMapper mapper;

	@Autowired
	private UsersUtil userUtil;
	
	@Override
	public User search(Long userId) {
		Optional<User> user = userRepository.findById(userId);
		return user.orElseThrow(() -> new IdNotFoudException(MessageUtil.ID_NOT_FOUND));
	}

	@Override
	public UserDTO searchUser(Long userId) {
		Optional<User> users = userRepository.findById(userId);
		users.orElseThrow(() -> new IdNotFoudException(MessageUtil.ID_NOT_FOUND));
		UserDTO usersDTO = new UserDTO();
		mapper.copyProperties(users, usersDTO);
		return usersDTO;
	}

	@Override
	public List<UserDTO> listsUsers() {
		return mapper.toDTOList(userRepository.findAll());
	}
	
	@Transactional
	@Override
	public UserDTO createUser(UserDTO userDTO) {
		var users = mapper.toEntity(userDTO);
		userUtil.verifyUserExist(users.getUser());
		userRepository.save(users);
		return mapper.toDTO(users);
	}
	
	@Transactional
	@Override
	public UserDTO updateUser(Long userId, UserDTO userDTO) {
		var users = userUtil.verifyUserExist(userId);
		mapper.copyProperties(userDTO, users);
		userRepository.save(users);
		return userDTO;
	}

	@Transactional
	@Override
	public void updatePassword(Long userId, UserPasswordDTO userPassword) {
		var user = userUtil.verifyUserExist(userId);
		if (!user.verifyPasswordCurrent(userPassword.getPasswordCurrent())) {
			throw new BusinessException(MessageUtil.INVALID_PASSWORD);
		}
		user.setPassword(userPassword.getPasswordNew());
	}

	@Transactional
	@Override
	public void deleteUser(Long userId) {
		try {
			userRepository.deleteById(userId);
		} 
		catch (DataIntegrityViolationException e) {
			throw new CannotDeleteException(MessageUtil.CANNOT_DELETE);
		} 
		catch (EmptyResultDataAccessException e) {
			throw new IdNotFoudException(MessageUtil.ID_NOT_FOUND);
		}
	}

}
