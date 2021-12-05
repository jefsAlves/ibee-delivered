package com.example.food.model.util;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.food.model.entities.State;
import com.example.food.model.exceptions.StateException;
import com.example.food.infra.repository.StateRepository;

@Component
public class ValidationState {

	@Autowired
	private StateRepository stateRepository;

	public boolean verifyStateExist(String name) {
		State stateValid = stateRepository.findByName(name);
		if (stateValid != null) {
			throw new StateException(MessageUtil.KITCHEN_ALREADY_EXIST);
		}

		return true;
	}

	public State verifyStateExist(Long id) {
		Optional<State> stateValid = stateRepository.findById(id);
		stateValid.orElseThrow(() -> new StateException(MessageUtil.ID_NOT_FOUND));

		return stateValid.get();
	}

}
