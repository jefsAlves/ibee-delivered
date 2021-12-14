package com.example.food.model.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.food.model.entities.Permissions;
import com.example.food.model.exceptions.IdNotFoudException;
import com.example.food.infra.repository.PermissionsRepository;

@Component
public class PermissionsUtil {

	@Autowired
	private PermissionsRepository permissionsRepository;

	public Permissions verifyPermissionsExist(Long permissionsId) {
		var permissions = permissionsRepository.findById(permissionsId);
		return permissions.orElseThrow(() -> new IdNotFoudException(MessageUtil.ID_NOT_FOUND));
	}
}
