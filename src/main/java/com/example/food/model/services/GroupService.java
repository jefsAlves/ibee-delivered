package com.example.food.model.services;

import java.util.List;

import com.example.food.api.dto.GroupDTO;
import com.example.food.model.entities.Group;

public interface GroupService {
	
	Group search(Long groupId);

	GroupDTO searchGroup(Long groupId);

	List<GroupDTO> listGroups();

	GroupDTO createGroup(GroupDTO groupsDTO);

	GroupDTO updateGroup(Long groupdId, GroupDTO groupsDTO);

	void deleteGroup(Long groupId);

}
