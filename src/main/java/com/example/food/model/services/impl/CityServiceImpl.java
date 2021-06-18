package com.example.food.model.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.food.model.entities.City;
import com.example.food.model.exceptions.BusinessException;
import com.example.food.model.exceptions.IdNotFoudException;
import com.example.food.model.repository.CityRepository;
import com.example.food.model.services.CityService;
import com.example.food.model.util.MessageUtil;
import com.example.food.model.util.ValidationCity;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private ValidationCity validationCity;

	@Override
	public City searchCity(Long id) {
		Optional<City> city = cityRepository.findById(id);
		return city.orElseThrow(() -> new IdNotFoudException(MessageUtil.ID_NOT_FOUND));
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
		validationCity.verifyCityExist(city.getName());
		return cityRepository.save(city);
	}

	@Transactional
	@Override
	public City updateCity(Long id, City city) {
		City cityValid = validationCity.verifyCityExist(id);
		BeanUtils.copyProperties(city, cityValid, "id", "state");
		return cityRepository.save(cityValid);
	}

	@Transactional
	@Override
	public void deleteCity(Long id) {
		try {
			cityRepository.deleteById(id);
		} 
		catch (EmptyResultDataAccessException e) {
			throw new IdNotFoudException(MessageUtil.ID_NOT_FOUND);
		}
	}

}
