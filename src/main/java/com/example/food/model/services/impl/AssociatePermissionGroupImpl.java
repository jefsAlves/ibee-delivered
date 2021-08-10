package com.example.food.model.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.food.model.dto.GroupDTO;
import com.example.food.model.mapper.GroupMapper;
import com.example.food.model.repository.GroupRepository;
import com.example.food.model.services.AssociatePermissionGroupService;
import com.example.food.model.services.GroupService;
import com.example.food.model.services.PermissionsService;

@Service
public class AssociatePermissionGroupImpl implements AssociatePermissionGroupService {

	@Autowired
	private GroupService groupService;

	@Autowired
	private PermissionsService permissionsService;

	@Autowired
	private GroupRepository permissionRepository;

	@Autowired
	private GroupMapper mapper;

	@Override
	public List<GroupDTO> listPermissionGroup(Long groupId) {
//		var permission = permissionRepository.searchPermissionGroup(groupId);
//		return mapper.toDTOList(permission);
		return null;
	}

	@Transactional
	@Override
	public void associatePermissionToGroup(Long groupId, Long permissionId) {
		var group = groupService.search(groupId);
		var permissions = permissionsService.search(permissionId);
		group.addPermission(permissions);
	}

	@Transactional
	@Override
	public void desassociatePermissiontoGroup(Long groupId, Long permissionId) {
		var groups = groupService.search(groupId);
		var permissions = permissionsService.search(permissionId);
		groups.removePermission(permissions);
	}

}
