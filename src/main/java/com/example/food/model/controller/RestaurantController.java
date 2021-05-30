package com.example.food.model.controller;

import java.util.List;

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

import com.example.food.model.entities.Restaurant;
import com.example.food.model.services.RestaurantService;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

	@Autowired
	private RestaurantService restarauntService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Restaurant> searchRestaraunt(@PathVariable Long id) {
		return new ResponseEntity<>(restarauntService.searchRestaurant(id), HttpStatus.OK);
	}

	@GetMapping(value = "/search")
	public ResponseEntity<Restaurant> searchRestaurant(@RequestParam(name = "findByName") String name) {
		return new ResponseEntity<>(restarauntService.searchRestaurantByName(name), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Restaurant>> listsRestaurants(Restaurant restaurant) {
		return new ResponseEntity<>(restarauntService.listsRestaurants(restaurant), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
		return new ResponseEntity<Restaurant>(restarauntService.createRestaurant(restaurant), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long id, @RequestBody Restaurant restaurant) {
		return new ResponseEntity<>(restarauntService.updateRestaurant(id, restaurant), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Restaurant> deleteRestaurant(@PathVariable Long id) {
		restarauntService.deleteRestaurant(id);
		return ResponseEntity.noContent().build();
	}

}
