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
import org.springframework.web.bind.annotation.RestController;

import com.example.food.model.entities.State;
import com.example.food.model.services.StateService;

@RestController
@RequestMapping(value = "/state")
public class StateController {

	@Autowired
	private StateService stateService;

	@GetMapping(value = "/{stateId}")
	public ResponseEntity<State> searchState(@PathVariable Long stateId) {
		return new ResponseEntity<>(stateService.searchState(stateId), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<State>> listStates(State state) {
		return new ResponseEntity<>(stateService.listStates(state), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<State> createState(@RequestBody State state) {
		return new ResponseEntity<>(stateService.createState(state), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{stateId}")
	public ResponseEntity<State> updateState(@PathVariable Long stateId, @RequestBody State state) {
		return new ResponseEntity<>(stateService.updateState(stateId, state), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{stateId}")
	public ResponseEntity<State> deleteState(@PathVariable Long stateId) {
		stateService.deleteState(stateId);
		return ResponseEntity.noContent().build();
	}

}
