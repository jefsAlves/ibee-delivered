package com.example.food.infra.mapper.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.food.infra.mapper.KitchenMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.food.api.dto.KitchenDTO;
import com.example.food.model.entities.Kitchen;

@Component
public class KitchenMapperImpl implements KitchenMapper {

	@Autowired
	private ModelMapper modelMapper;

	public KitchenDTO toDTO(Kitchen kitchen) {
		return modelMapper.map(kitchen, KitchenDTO.class);
	}

	public Kitchen toEntity(KitchenDTO kitchenDTO) {
		return modelMapper.map(kitchenDTO, Kitchen.class);
	}

	public List<KitchenDTO> toDTOList(List<Kitchen> kitchen) {
		return kitchen.stream().map(src -> toDTO(src)).collect(Collectors.toList());
	}

	public List<Kitchen> toEntityList(List<KitchenDTO> kitchenDTO) {
		return kitchenDTO.stream().map(src -> toEntity(src)).collect(Collectors.toList());
	}

	public KitchenDTO toDTO(Optional<Kitchen> kitchen) {
		KitchenDTO kitchenDTO = new KitchenDTO();
		kitchenDTO.setId(kitchen.get().getId());
		kitchenDTO.setName(kitchen.get().getName());
		return kitchenDTO;
	}

	public void copyProperties(KitchenDTO kitchenDTO, Kitchen kitchen) {
		modelMapper.map(kitchenDTO.getName(), kitchen.getName());
	}

}
