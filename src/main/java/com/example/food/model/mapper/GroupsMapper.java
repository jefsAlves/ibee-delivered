package com.example.food.model.mapper;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.food.model.dto.GroupsDTO;
import com.example.food.model.entities.Groups;

@Component
public class GroupsMapper {

	@Autowired
	private ModelMapper modelMapper;

	public GroupsDTO toDTO(Groups groups) {
		return modelMapper.map(groups, GroupsDTO.class);
	}

	public Groups toEntity(GroupsDTO groupsDTO) {
		return modelMapper.map(groupsDTO, Groups.class);
	}

	public List<GroupsDTO> toDTOList(List<Groups> groups) {
		return modelMapper.map(groups, new TypeToken<List<Groups>>() {}.getType());
	}
	
	public List<GroupsDTO> toEntityList(List<GroupsDTO> groupsDTO) {
		return modelMapper.map(groupsDTO, new TypeToken<List<GroupsDTO>>() {}.getType());
	}
	
	public void toDTO(Optional<Groups> groups, GroupsDTO groupsDTO) {
		modelMapper.map(groups, groupsDTO);
	}

}
