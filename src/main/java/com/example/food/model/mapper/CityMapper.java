package com.example.food.model.mapper;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.food.model.dto.CityDTO;
import com.example.food.model.entities.City;
import com.example.food.model.entities.State;

@Component
public class CityMapper {

	@Autowired
	private ModelMapper modelMapper;

	public CityDTO toDTO(Optional<City> city) {
		return modelMapper.map(city, CityDTO.class);
	}

	public CityDTO toDTO(City city) {
		return modelMapper.map(city, CityDTO.class);
	}

	public City toEntity(CityDTO cityDTO) {
		return modelMapper.map(cityDTO, City.class);
	}

	public List<City> toEntity(List<CityDTO> cityDTO) {
		return modelMapper.map(cityDTO, new TypeToken<List<CityDTO>>() {
		}.getType());
	}

	public List<CityDTO> toDTOList(List<City> city) {
		return modelMapper.map(city, new TypeToken<List<City>>() {
		}.getType());
	}

	public void copyProperties(CityDTO cityDTO, City city) {
		city.setState(new State());
		modelMapper.map(cityDTO, city);
	}

}
