package com.example.food.model.services;

import java.util.List;

import com.example.food.model.dto.CityDTO;

public interface CityService {

	CityDTO searchCity(Long id);

	CityDTO searchCity(String name);

	List<CityDTO> listsCity();

	CityDTO createCity(CityDTO city);

	CityDTO updateCity(Long id, CityDTO city);

	void deleteCity(Long id);

}
