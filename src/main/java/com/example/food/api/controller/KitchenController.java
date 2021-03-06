package com.example.food.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.example.food.infra.mapper.KitchenMapper;
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

import com.example.food.api.dto.KitchenDTO;
import com.example.food.model.services.KitchenService;

@RestController
@RequestMapping(value = "/kitchen")
public class KitchenController {

	@Autowired
	private KitchenService kitchenService;

	@Autowired
	private KitchenMapper mapper;

	@GetMapping(value = "/{kitchenId}")
	public ResponseEntity<KitchenDTO> searchKitchen(@PathVariable Long kitchenId) {
		return new ResponseEntity<>(kitchenService.searchKitchen(kitchenId), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<KitchenDTO>> listKitchen(KitchenDTO kitchenDTO) {
		return new ResponseEntity<>(kitchenService.listKitchens(kitchenDTO), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<KitchenDTO> createKitchen(@RequestBody @Valid KitchenDTO kitchenDTO) {
		return new ResponseEntity<>(kitchenService.createKitchen(kitchenDTO), HttpStatus.CREATED);
	}

//	@PostMapping
//	public ResponseEntity<KitchenDTO> createKitchen(@Valid KitchenDTO kitchenDTO) {
//		var kitchen = kitchenService.createKitchen(mapper.toEntity(kitchenDTO));
//		return ResponseEntity.ok(mapper.toDTO(kitchen));
////		return null;
//	}

	@PutMapping(value = "/{kitchenId}")
	public ResponseEntity<KitchenDTO> updateKitchen(@PathVariable Long kitchenId, @RequestBody KitchenDTO kitchenDTO) {
		return new ResponseEntity<>(kitchenService.updateKitchen(kitchenId, kitchenDTO), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{kitchenId}")
	public ResponseEntity<KitchenDTO> deleteKitchen(@PathVariable Long kitchenId) {
		kitchenService.deleteKitchen(kitchenId);
		return ResponseEntity.noContent().build();
	}

}
