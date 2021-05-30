package com.example.food.model.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.food.model.entities.City;
import com.example.food.model.exceptions.BusinessException;
import com.example.food.model.repository.CityRepository;
import com.example.food.model.services.CityService;
import com.example.food.model.util.MessageUtil;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityRepository cityRepository;

	@Override
	public City searchCity(Long id) {
		Optional<City> city = cityRepository.findById(id);
		return city.get();
	}

	@Override
	public City searchCity(String name) {
		City nameBase = cityRepository.searchName(name);
		if (nameBase == null) {
			throw new BusinessException(MessageUtil.CITY_NOT_EXIST);
		}
		return nameBase;
	}

	@Override
	public List<City> listsCity() {
		return cityRepository.findAll();
	}

	@Transactional
	@Override
	public City createCity(City city) {
		return validCityAlreayExists(city);
	}

	private City validCityAlreayExists(City city) {
		City cityBase = cityRepository.findByName(city.getName());
		if (cityBase != null) {
			throw new BusinessException(MessageUtil.CITY_ALREADY_EXIST);
		}
		return cityRepository.save(city);
	}

	@Transactional
	@Override
	public City updateCity(Long id, City city) {
		Optional<City> cityBase = cityRepository.findById(id);
		if (cityBase.isPresent()) {
			BeanUtils.copyProperties(city, cityBase.get(), "id");
			City cityOrigin = cityRepository.save(cityBase.get());
			return cityOrigin;
		}
		throw new BusinessException(MessageUtil.ID_NOT_FOUND);
	}

	@Transactional
	@Override
	public void deleteCity(Long id) {
	}

}
