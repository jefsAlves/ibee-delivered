package com.example.food.model.services;

import java.util.List;

import com.example.food.api.dto.PermissionsDTO;
import com.example.food.model.entities.Permissions;

public interface PermissionsService {

	Permissions search(Long permissionId);

	PermissionsDTO searchPermission(Long permissionId);

	List<PermissionsDTO> listPermissions();

	PermissionsDTO createPermission(PermissionsDTO permissionsDTO);

	PermissionsDTO updatePermission(Long permissionId, PermissionsDTO permissionDTO);

	void deletePermission(Long permissionId);

}
