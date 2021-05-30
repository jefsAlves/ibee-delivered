package com.example.food.model.services;

import java.util.List;

import com.example.food.model.entities.State;

public interface StateService {

	State searchState(Long id);

	List<State> listStates(State state);

	State createState(State state);

	State updateState(Long id, State state);

	void deleteState(Long id);

}
