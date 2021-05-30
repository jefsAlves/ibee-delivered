package com.example.food.model.services;

import java.util.List;

import com.example.food.model.entities.City;

public interface CityService {

	City searchCity(Long id);

	City searchCity(String name);

	List<City> listsCity();

	City createCity(City city);

	City updateCity(Long id, City city);

	void deleteCity(Long id);

}
