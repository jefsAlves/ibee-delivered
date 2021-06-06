package com.example.food.model.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.food.model.entities.State;
import com.example.food.model.exceptions.IdNotFoudException;
import com.example.food.model.repository.StateRepository;
import com.example.food.model.services.StateService;
import com.example.food.model.util.MessageUtil;
import com.example.food.model.util.ValidationState;

@Service
public class StateServiceImpl implements StateService {

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private ValidationState validationState;

	@Override
	public State searchState(Long id) {
		Optional<State> state = stateRepository.findById(id);
		return state.orElseThrow(() -> new IdNotFoudException(MessageUtil.ID_NOT_FOUND));
	}

	@Override
	public List<State> listStates(State state) {
		return stateRepository.findAll();
	}

	@Transactional
	@Override
	public State createState(State state) {
		validationState.verifyStateExist(state.getName());
		return stateRepository.save(state);
	}

	@Transactional
	@Override
	public State updateState(Long id, State state) {
		State stateValid = validationState.verifyStateExist(id);
		BeanUtils.copyProperties(state, stateValid, "id");
		return stateRepository.save(stateValid);
	}

	@Transactional
	@Override
	public void deleteState(Long id) {
		try {
			stateRepository.findById(id);
		} 
		catch (EmptyResultDataAccessException e) {
			throw new IdNotFoudException(MessageUtil.ID_NOT_FOUND);
		}
	}

}
