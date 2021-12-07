package com.example.food.infra.mapper.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.food.api.dto.GroupDTO;
import com.example.food.model.entities.Group;

@Component
public class GroupMapper {

	@Autowired
	private ModelMapper modelMapper;

	public GroupDTO toDTO(Group groups) {
		return modelMapper.map(groups, GroupDTO.class);
	}

	public Group toEntity(GroupDTO groupsDTO) {
		return modelMapper.map(groupsDTO, Group.class);
	}

	public List<GroupDTO> toDTOList(List<Group> groups) {
		return modelMapper.map(groups, new TypeToken<List<Group>>() {}.getType());
	}

	public List<GroupDTO> toEntityList(List<GroupDTO> groupsDTO) {
		return modelMapper.map(groupsDTO, new TypeToken<List<GroupDTO>>() {}.getType());
	}
	
	public Set<GroupDTO> toDTOSet(Set<Group> groups) {
		return modelMapper.map(groups, new TypeToken<Set<Group>>() {}.getType());
	}
	
	public Set<Group> toEntitySet(Set<GroupDTO> groupsDTO) {
		return modelMapper.map(groupsDTO, new TypeToken<Set<GroupDTO>>() {}.getType());
	}
	
	public GroupDTO toDTO(Optional<Group> groups) {
		GroupDTO groupsDTO = new GroupDTO();
		groupsDTO.setId(groups.get().getId());
		groupsDTO.setName(groups.get().getName());
		return groupsDTO;
	}
	
	public void copyProperties(GroupDTO groupsDTO, Group groups) {
		BeanUtils.copyProperties(groupsDTO, groups, "id", "permissions");
	}

}
