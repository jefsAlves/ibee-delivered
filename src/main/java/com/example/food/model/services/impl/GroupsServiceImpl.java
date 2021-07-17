package com.example.food.model.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.food.model.dto.GroupsDTO;
import com.example.food.model.entities.Groups;
import com.example.food.model.mapper.GroupsMapper;
import com.example.food.model.repository.GroupsRepository;
import com.example.food.model.services.GroupsService;

@Service
public class GroupsServiceImpl implements GroupsService {

	@Autowired
	private GroupsRepository groupsRepository;

	@Autowired
	private GroupsMapper mapper;

	@Override
	public List<GroupsDTO> listGroups() {
		var groups = groupsRepository.findAll();
		return mapper.toDTOList(groups);
	}

	@Override
	public GroupsDTO searchGroups(Long groupId) {
		Optional<Groups> groups = groupsRepository.findById(groupId);
		GroupsDTO groupsDTO = new GroupsDTO();
		mapper.toDTO(groups, groupsDTO);
		return groupsDTO;
	}

	@Override
	public GroupsDTO createGroup(GroupsDTO groupsDTO) {
		return null;
	}

	@Override
	public GroupsDTO updateGroups(GroupsDTO groupsDTO) {
		return null;
	}

	@Override
	public void deleteGroup(Long groupId) {

	}

}
