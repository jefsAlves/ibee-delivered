package com.example.food.model.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.food.model.dto.UserDTO;
import com.example.food.model.dto.UserPasswordDTO;
import com.example.food.model.entities.User;
import com.example.food.model.exceptions.BusinessException;
import com.example.food.model.exceptions.CannotDeleteException;
import com.example.food.model.exceptions.IdNotFoudException;
import com.example.food.model.mapper.UsersMapper;
import com.example.food.model.repository.UsersRepository;
import com.example.food.model.services.UsersService;
import com.example.food.model.util.MessageUtil;
import com.example.food.model.util.UsersUtil;

@Service
public class UserServiceImpl implements UsersService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private UsersMapper mapper;

	@Autowired
	private UsersUtil userUtil;

	@Override
	public List<UserDTO> listsUsers() {
		return mapper.toDTOList(usersRepository.findAll());
	}

	@Override
	public UserDTO searchUser(Long userId) {
		Optional<User> users = usersRepository.findById(userId);
		users.orElseThrow(() -> new IdNotFoudException(MessageUtil.ID_NOT_FOUND));
		UserDTO usersDTO = new UserDTO();
		mapper.copyProperties(users, usersDTO);
		return usersDTO;
	}

	@Transactional
	@Override
	public UserDTO createUser(UserDTO userDTO) {
		var users = mapper.toEntity(userDTO);
		userUtil.verifyUserExist(users.getUser());
		usersRepository.save(users);
		return mapper.toDTO(users);
	}
	
	@Transactional
	@Override
	public UserDTO updateUser(Long userId, UserDTO userDTO) {
		var users = userUtil.verifyUserExist(userId);
		mapper.copyProperties(userDTO, users);
		usersRepository.save(users);
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
			usersRepository.deleteById(userId);
		} 
		catch (DataIntegrityViolationException e) {
			throw new CannotDeleteException(MessageUtil.CANNOT_DELETE);
		} 
		catch (EmptyResultDataAccessException e) {
			throw new IdNotFoudException(MessageUtil.ID_NOT_FOUND);
		}
	}

}
