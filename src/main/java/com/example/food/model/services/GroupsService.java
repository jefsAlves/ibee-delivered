package com.example.food.model.services;

import java.util.List;

import com.example.food.model.dto.GroupsDTO;

public interface GroupsService {

	List<GroupsDTO> listGroups();

	GroupsDTO searchGroups(Long groupId);

	GroupsDTO createGroup(GroupsDTO groupsDTO);

	GroupsDTO updateGroups(GroupsDTO groupsDTO);

	void deleteGroup(Long groupId);

}
