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
import org.springframework.web.bind.annotation.RestController;

import com.example.food.model.entities.Kitchen;
import com.example.food.model.services.KitchenService;

@RestController
@RequestMapping(value = "/kitchen")
public class KitchenController {

	@Autowired
	private KitchenService kitchenService;

	@GetMapping(value = "/{kitchenId}")
	public ResponseEntity<Kitchen> searchKitchen(@PathVariable Long kitchenId) {
		return new ResponseEntity<>(kitchenService.searchKitchen(kitchenId), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Kitchen>> listKitchen(Kitchen kitchen) {
		return new ResponseEntity<>(kitchenService.listKitchens(kitchen), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Kitchen> createKitchen(@RequestBody @Valid Kitchen kitchen) {
		return new ResponseEntity<>(kitchenService.createKitchen(kitchen), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{kitchenId}")
	public ResponseEntity<Kitchen> updateKitchen(@PathVariable Long kitchenId, @RequestBody Kitchen kitchen) {
		return new ResponseEntity<>(kitchenService.updateKitchen(kitchenId, kitchen), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{kitchenId}")
	public ResponseEntity<Kitchen> deleteKitchen(@PathVariable Long kitchenId) {
		kitchenService.deleteKitchen(kitchenId);
		return ResponseEntity.noContent().build();
	}

}
