package com.example.food.model.services;

import java.util.List;

import com.example.food.model.dto.GroupDTO;

public interface AssociatePermissionGroupService {

	List<GroupDTO> listPermissionGroup(Long groupId);

	void associatePermissionToGroup(Long groupId, Long permissionId);

	void desassociatePermissiontoGroup(Long groupId, Long permissionId);
}
