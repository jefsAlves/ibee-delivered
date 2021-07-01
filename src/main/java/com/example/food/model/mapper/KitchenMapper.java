package com.example.food.model.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.food.model.dto.KitchenDTO;
import com.example.food.model.entities.Kitchen;

@Component
public class KitchenMapper {

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

}
