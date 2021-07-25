package com.example.food.model.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.food.model.dto.GroupsDTO;
import com.example.food.model.entities.Groups;
import com.example.food.model.exceptions.CannotDeleteException;
import com.example.food.model.exceptions.IdNotFoudException;
import com.example.food.model.mapper.GroupsMapper;
import com.example.food.model.repository.GroupsRepository;
import com.example.food.model.services.GroupsService;
import com.example.food.model.util.GroupsUtil;
import com.example.food.model.util.MessageUtil;

@Service
public class GroupsServiceImpl implements GroupsService {

	@Autowired
	private GroupsRepository groupsRepository;

	@Autowired
	private GroupsMapper mapper;

	@Autowired
	private GroupsUtil groupsUtil;

	@Override
	public List<GroupsDTO> listGroups() {
		var groups = groupsRepository.findAll();
		return mapper.toDTOList(groups);
	}

	@Override
	public GroupsDTO searchGroups(Long groupId) {
		Optional<Groups> groups = groupsRepository.findById(groupId);
		groups.orElseThrow(() -> new IdNotFoudException(MessageUtil.ID_NOT_FOUND));
		return mapper.toDTO(groups);
	}

	@Override
	public GroupsDTO createGroup(GroupsDTO groupsDTO) {
		var groups = groupsRepository.save(mapper.toEntity(groupsDTO));
		return mapper.toDTO(groups);
	}

	@Override
	public GroupsDTO updateGroups(Long groupId, GroupsDTO groupsDTO) {
		var groups = groupsUtil.verifyGroupsExist(groupId);
		mapper.copyProperties(groupsDTO, groups);
		groupsRepository.save(groups);
		return mapper.toDTO(groups);
	}

	@Override
	public void deleteGroup(Long groupId) {
		try {
			groupsRepository.deleteById(groupId);
		}
		catch (DataIntegrityViolationException e) {
			throw new CannotDeleteException(e.getMessage());
		}
		catch(EmptyResultDataAccessException e) {
			throw new CannotDeleteException(MessageUtil.ID_NOT_FOUND);
		}
	}

}
