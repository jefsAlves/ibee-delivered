package com.example.food.model.util;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.food.model.entities.City;
import com.example.food.model.exceptions.CityException;
import com.example.food.model.repository.CityRepository;

@Component
public class ValidationCity {

	@Autowired
	private CityRepository cityRepository;

	public boolean verifyCityExist(String name) {
		City cityValid = cityRepository.findByName(name);
		if (cityValid != null) {
			throw new CityException(MessageUtil.KITCHEN_ALREADY_EXIST);
		}

		return true;
	}

	public City verifyCityExist(Long id) {
		Optional<City> cityValid = cityRepository.findById(id);
		cityValid.orElseThrow(() -> new CityException(MessageUtil.ID_NOT_FOUND));

		return cityValid.get();
	}

}
