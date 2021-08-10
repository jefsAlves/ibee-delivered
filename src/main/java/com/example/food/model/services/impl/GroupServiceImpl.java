package com.example.food.model.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.food.model.dto.GroupDTO;
import com.example.food.model.entities.Group;
import com.example.food.model.exceptions.CannotDeleteException;
import com.example.food.model.exceptions.IdNotFoudException;
import com.example.food.model.mapper.GroupMapper;
import com.example.food.model.repository.GroupRepository;
import com.example.food.model.services.GroupService;
import com.example.food.model.util.GroupUtil;
import com.example.food.model.util.MessageUtil;

@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private GroupMapper mapper;

	@Autowired
	private GroupUtil groupUtil;
	
	@Override
	public Group search(Long groupId) {
		Optional<Group> group = groupRepository.findById(groupId);
		return group.orElseThrow(() -> new IdNotFoudException(MessageUtil.ID_NOT_FOUND));
	}

	@Override
	public GroupDTO searchGroup(Long groupId) {
		Optional<Group> groups = groupRepository.findById(groupId);
		groups.orElseThrow(() -> new IdNotFoudException(MessageUtil.ID_NOT_FOUND));
		return mapper.toDTO(groups);
	}
	
	@Override
	public List<GroupDTO> listGroups() {
		var groups = groupRepository.findAll();
		return mapper.toDTOList(groups);
	}

	@Override
	public GroupDTO createGroup(GroupDTO groupsDTO) {
		var groups = groupRepository.save(mapper.toEntity(groupsDTO));
		return mapper.toDTO(groups);
	}

	@Override
	public GroupDTO updateGroup(Long groupId, GroupDTO groupsDTO) {
		var groups = groupUtil.verifyGroupsExist(groupId);
		mapper.copyProperties(groupsDTO, groups);
		groupRepository.save(groups);
		return mapper.toDTO(groups);
	}

	@Override
	public void deleteGroup(Long groupId) {
		try {
			groupRepository.deleteById(groupId);
		}
		catch (DataIntegrityViolationException e) {
			throw new CannotDeleteException(e.getMessage());
		}
		catch(EmptyResultDataAccessException e) {
			throw new CannotDeleteException(MessageUtil.ID_NOT_FOUND);
		}
	}

}
