package com.example.food.model.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import javax.transaction.Transactional;

import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.food.infra.kafka.integration.SendCity;
import com.example.food.api.dto.CityDTO;
import com.example.food.model.entities.City;
import com.example.food.model.exceptions.IdNotFoudException;
import com.example.food.infra.mapper.impl.CityMapper;
import com.example.food.infra.repository.CityRepository;
import com.example.food.model.services.CityService;
import com.example.food.model.util.MessageUtil;
import com.example.food.model.util.ValidationCity;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private ValidationCity validationCity;

	@Autowired
	private CityMapper mapper;

	@Autowired
	private SendCity integration;

//	private Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);

	@Override
	public CityDTO searchCity(Long id) {
		Optional<City> city = cityRepository.findById(id);
		city.orElseThrow(() -> new IdNotFoudException(MessageUtil.ID_NOT_FOUND));
		return mapper.toDTO(city);
	}

	@Override
	public CityDTO searchCity(String name) {
		City nameBase = cityRepository.searchName(name);
		validationCity.verifyCityExist(name);
		return mapper.toDTO(nameBase);
	}

	@Override
	public List<CityDTO> listsCity() {
		var city = cityRepository.findAll();
		return mapper.toDTOList(city);
	}

	@Transactional
	@Override
	public CityDTO createCity(CityDTO cityDTO) throws InterruptedException, ExecutionException {
		var city = mapper.toEntity(cityDTO);
		validationCity.verifyCityExist(city.getName());
		cityRepository.save(city);
		integration.sendMessage(cityDTO);
		return mapper.toDTO(city);
	}

	@Transactional
	@Override
	public CityDTO updateCity(Long id, CityDTO cityDTO) {
		City cityValid = validationCity.verifyCityExist(id);
		mapper.copyProperties(cityDTO, cityValid);
		cityValid = cityRepository.save(cityValid);
		return mapper.toDTO(cityValid);
	}

	@Transactional
	@Override
	public void deleteCity(Long id) {
		try {
			cityRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new IdNotFoudException(MessageUtil.ID_NOT_FOUND);
		}
	}

}
