package com.example.food.api.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

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

import com.example.food.api.dto.StateDTO;
import com.example.food.model.services.StateService;

@RestController
@RequestMapping(value = "/state")
public class StateController {

	@Autowired
	private StateService stateService;

	@GetMapping(value = "/{stateId}")
	public ResponseEntity<StateDTO> searchState(@PathVariable Long stateId) {
		return new ResponseEntity<>(stateService.searchState(stateId), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<StateDTO>> listStates() {
		return new ResponseEntity<>(stateService.listStates(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<StateDTO> createState(@RequestBody @Valid StateDTO stateDTO) throws InterruptedException, ExecutionException {
		return new ResponseEntity<>(stateService.createState(stateDTO), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{stateId}")
	public ResponseEntity<StateDTO> updateState(@PathVariable Long stateId, @Valid @RequestBody StateDTO stateDTO) {
		return new ResponseEntity<>(stateService.updateState(stateId, stateDTO), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{stateId}")
	public ResponseEntity<StateDTO> deleteState(@PathVariable Long stateId) {
		stateService.deleteState(stateId);
		return ResponseEntity.noContent().build();
	}

}
