package com.example.food.model.services.impl;

import javax.transaction.Transactional;

import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.food.infra.mapper.impl.GroupMapper;
import com.example.food.infra.repository.GroupRepository;
import com.example.food.model.services.AssociateGroupUserService;
import com.example.food.model.services.GroupService;
import com.example.food.model.services.UserService;

@Service
public class AssociateGroupUserServiceImpl implements AssociateGroupUserService {

	@Autowired
	private GroupService groupService;

	@Autowired
	private UserService userService;

	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private GroupMapper mapper;

//	@Override
//	public List<GroupsDTO> listGroups(Long groupId) {
//		var group = groupRepository.findById(groupId);
//		return mapper.toDTOListOptional(group);
//	}

	@Transactional
	@Override
	public void associateGroupToUser(Long userId, Long groupId) {
		var user = userService.search(userId);
		var group = groupService.search(groupId);
		user.addGroup(group);
	}

	@Transactional
	@Override
	public void desassociateGroupToUser(Long userId, Long groupId) {
		var user = userService.search(userId);
		var group = groupService.search(groupId);
		user.removeGroup(group);
	}

}
