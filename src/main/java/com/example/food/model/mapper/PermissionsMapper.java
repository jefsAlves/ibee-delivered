package com.example.food.model.mapper;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.food.model.dto.PermissionsDTO;
import com.example.food.model.entities.Permissions;

@Component
public class PermissionsMapper {

	@Autowired
	private ModelMapper modelMapper;

	public PermissionsDTO toDTO(Permissions permissions) {
		return modelMapper.map(permissions, PermissionsDTO.class);
	}

	public Permissions toEntity(PermissionsDTO permissionsDTO) {
		return modelMapper.map(permissionsDTO, Permissions.class);
	}

	public List<PermissionsDTO> toDTOList(List<Permissions> permissions) {
		return modelMapper.map(permissions, new TypeToken<List<Permissions>>() {
		}.getType());
	}

	public List<Permissions> toEntityList(List<PermissionsDTO> permissionsDTO) {
		return modelMapper.map(permissionsDTO, new TypeToken<List<PermissionsDTO>>() {
		}.getType());
	}

	public PermissionsDTO toDTOOptional(Optional<Permissions> permissions) {
		PermissionsDTO permissionsDTO = new PermissionsDTO();
		permissionsDTO.setId(permissions.get().getId());
		permissionsDTO.setDescription(permissions.get().getDescription());
		return permissionsDTO;
	}

	public void copyProperties(PermissionsDTO permissionsDTO, Permissions permissions) {
		BeanUtils.copyProperties(permissionsDTO, permissions, "id");
	}

}
