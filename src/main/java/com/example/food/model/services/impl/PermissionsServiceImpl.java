package com.example.food.model.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.food.model.dto.PermissionsDTO;
import com.example.food.model.entities.Permissions;
import com.example.food.model.exceptions.IdNotFoudException;
import com.example.food.model.mapper.PermissionsMapper;
import com.example.food.model.repository.PermissionsRepository;
import com.example.food.model.services.PermissionsService;
import com.example.food.model.util.MessageUtil;
import com.example.food.model.util.PermissionsUtil;

@Service
public class PermissionsServiceImpl implements PermissionsService {

	@Autowired
	private PermissionsRepository permissionsRepository;

	@Autowired
	private PermissionsMapper mapper;

	@Autowired
	private PermissionsUtil permissionsUtil;

	@Override
	public Permissions search(Long permissionId) {
		Optional<Permissions> permissions = permissionsRepository.findById(permissionId);
		return permissions.orElseThrow(() -> new IdNotFoudException(MessageUtil.ID_NOT_FOUND));
	}

	@Override
	public PermissionsDTO searchPermission(Long permissionId) {
		Optional<Permissions> permissions = permissionsRepository.findById(permissionId);
		permissions.orElseThrow(() -> new IdNotFoudException(MessageUtil.ID_NOT_FOUND));
		return mapper.toDTOOptional(permissions);
	}

	@Override
	public List<PermissionsDTO> listPermissions() {
		return mapper.toDTOList(permissionsRepository.findAll());
	}

	@Transactional
	@Override
	public PermissionsDTO createPermission(PermissionsDTO permissionsDTO) {
		var permissions = permissionsRepository.save(mapper.toEntity(permissionsDTO));
		return mapper.toDTO(permissions);
	}

	@Transactional
	@Override
	public PermissionsDTO updatePermission(Long permissionId, PermissionsDTO permissionDTO) {
		var permissionsBase = permissionsUtil.verifyPermissionsExist(permissionId);
		mapper.copyProperties(permissionDTO, permissionsBase);
		return mapper.toDTO(permissionsBase);
	}

	@Transactional
	@Override
	public void deletePermission(Long permissionId) {
		try {
			permissionsRepository.deleteById(permissionId);
		} catch (EmptyResultDataAccessException e) {
			throw new IdNotFoudException(MessageUtil.ID_NOT_FOUND);
		}

	}

}
