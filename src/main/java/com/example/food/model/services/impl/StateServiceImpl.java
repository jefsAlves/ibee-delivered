package com.example.food.model.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.food.model.entities.State;
import com.example.food.model.exceptions.BusinessException;
import com.example.food.model.repository.StateRepository;
import com.example.food.model.services.StateService;
import com.example.food.model.util.MessageUtil;

@Service
public class StateServiceImpl implements StateService {

	@Autowired
	private StateRepository stateRepository;

	@Override
	public State searchState(Long id) {
		Optional<State> state = stateRepository.findById(id);
		return state.orElseThrow(() -> new BusinessException(MessageUtil.ID_NOT_FOUND));
	}

	@Override
	public List<State> listStates(State state) {
		return stateRepository.findAll();
	}

	@Transactional
	@Override
	public State createState(State state) {
		validStateExist(state);
		return stateRepository.save(state);
	}

	private void validStateExist(State state) {
		State stateBase = stateRepository.findByName(state.getName());
		if (stateBase != null) {
			throw new BusinessException(MessageUtil.STATE_ALREADY_EXIST);
		}
	}

	@Transactional
	@Override
	public State updateState(Long id, State state) {
		Optional<State> stateBase = stateRepository.findById(id);
		if (stateBase.isPresent()) {
			BeanUtils.copyProperties(state, stateBase.get(), "id");
			return stateRepository.save(stateBase.get());
		}
		throw new BusinessException(MessageUtil.ID_NOT_FOUND);
	}

	@Transactional
	@Override
	public void deleteState(Long id) {
		Optional<State> stateBase = stateRepository.findById(id);
		if (stateBase == null) {
			throw new BusinessException(MessageUtil.ID_NOT_FOUND);
		}
		stateRepository.deleteById(id);
	}

}
