package com.example.food.api.controller;

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

import com.example.food.api.dto.RestaurantDTO;
import com.example.food.model.entities.Restaurant;
import com.example.food.model.services.RestaurantService;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<RestaurantDTO> searchRestaraunt(@PathVariable Long id) {
		return new ResponseEntity<>(restaurantService.searchRestaurant(id), HttpStatus.OK);
	}

	@GetMapping(value = "/search")
	public ResponseEntity<RestaurantDTO> searchRestaurant(@RequestParam(name = "name") String name) {
		return new ResponseEntity<>(restaurantService.searchRestaurantByName(name), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Restaurant>> listsRestaurants(Restaurant restaurant) {
		return new ResponseEntity<>(restaurantService.listRestaurants(restaurant), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<RestaurantDTO> createRestaurant(@RequestBody @Valid RestaurantDTO restaurantDTO) {
		return new ResponseEntity<>(restaurantService.createRestaurant(restaurantDTO), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<RestaurantDTO> updateRestaurant(@PathVariable Long id,
			@Valid @RequestBody RestaurantDTO restaurantDTO) {
		return new ResponseEntity<>(restaurantService.updateRestaurant(id, restaurantDTO), HttpStatus.OK);
	}

	@PutMapping(value = "/{activeId}/active")
	public ResponseEntity<RestaurantDTO> activeRestaurant(@PathVariable Long activeId) {
		restaurantService.activeRestaurant(activeId);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{desactiveId}/desactive")
	public ResponseEntity<RestaurantDTO> desactiveRestaurant(@PathVariable Long desactiveId) {
		restaurantService.desactiveRestaurant(desactiveId);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/active")
	public ResponseEntity<Void> activeRestaurantAll(@RequestBody List<Long> restaurantIds) {
		restaurantService.activeAll(restaurantIds);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "/desactive")
	public ResponseEntity<Void> desactiveRestaurantAll(@RequestBody List<Long> restaurantIds) {
		restaurantService.desactiveRestaurantAll(restaurantIds);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<RestaurantDTO> deleteRestaurant(@PathVariable Long id) {
		restaurantService.deleteRestaurant(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{restaurantId}/open")
	public ResponseEntity<Void> openRestaurant(@PathVariable Long restaurantId) {
		restaurantService.openRestaurant(restaurantId);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{restaurantId}/close")
	public ResponseEntity<Void> closeRestaurant(@PathVariable Long restaurantId) {
		restaurantService.closeRestaurant(restaurantId);
		return ResponseEntity.noContent().build();
	}

}
