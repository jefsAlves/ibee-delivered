package com.example.food.model.services;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.example.food.model.dto.StateDTO;

public interface StateService {

	StateDTO searchState(Long id);

	List<StateDTO> listStates();

	StateDTO createState(StateDTO stateDTO) throws InterruptedException, ExecutionException;

	StateDTO updateState(Long id, StateDTO ststateDTOastateDTOte);

	void deleteState(Long id);

}
