package com.example.food.model.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.food.model.entities.City;
import com.example.food.model.services.CityService;

@RestController
@RequestMapping("/city")
public class CityController {

	@Autowired
	private CityService cityService;

	@GetMapping(value = "/{cityId}")
	public ResponseEntity<City> searchCity(@PathVariable Long cityId) {
		return new ResponseEntity<>(cityService.searchCity(cityId), HttpStatus.OK);
	}

	@GetMapping(value = "/search")
	public ResponseEntity<City> searchCity(@RequestParam(value = "findByName") String name) {
		return new ResponseEntity<>(cityService.searchCity(name), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<City>> listsCity(City city) {
		return new ResponseEntity<>(cityService.listsCity(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<City> createCity(@RequestBody @Valid City city) {
		return new ResponseEntity<>(cityService.createCity(city), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{cityId}")
	public ResponseEntity<City> updateCity(@PathVariable @Valid Long cityId, @RequestBody City city) {
		return new ResponseEntity<>(cityService.updateCity(cityId, city), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{cityId}")
	public ResponseEntity<City> deleteCity(@PathVariable Long cityId) {
		cityService.deleteCity(cityId);
		return ResponseEntity.noContent().build();
	}

}
