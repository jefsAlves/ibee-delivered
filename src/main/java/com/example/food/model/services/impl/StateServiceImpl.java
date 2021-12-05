package com.example.food.model.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.food.config.integration.SendState;
import com.example.food.api.dto.StateDTO;
import com.example.food.model.entities.State;
import com.example.food.model.exceptions.IdNotFoudException;
import com.example.food.infra.mapper.StateMapper;
import com.example.food.infra.repository.StateRepository;
import com.example.food.model.services.StateService;
import com.example.food.model.util.MessageUtil;
import com.example.food.model.util.ValidationState;

@Service
public class StateServiceImpl implements StateService {

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private ValidationState validationState;

	@Autowired
	private StateMapper mapper;

	@Autowired
	private SendState integration;

	@Override
	public StateDTO searchState(Long id) {
		Optional<State> state = stateRepository.findById(id);
		state.orElseThrow(() -> new IdNotFoudException(MessageUtil.ID_NOT_FOUND));
		return mapper.toDTO(state);
	}

	@Override
	public List<StateDTO> listStates() {
		var state = stateRepository.findAll();
		return mapper.toDTOList(state);
	}

	@Transactional
	@Override
	public StateDTO createState(StateDTO stateDTO) throws InterruptedException, ExecutionException {
		var state = mapper.toEntity(stateDTO);
		validationState.verifyStateExist(state.getName());
		stateRepository.save(state);
		integration.sendMessage(stateDTO);
		return mapper.toDTO(state);
	}

	@Transactional
	@Override
	public StateDTO updateState(Long id, StateDTO stateDTO) {
		var stateBase = validationState.verifyStateExist(id);
		mapper.copyProperties(stateDTO, stateBase);
		stateRepository.save(stateBase);
		return mapper.toDTO(stateBase);
	}

	@Transactional
	@Override
	public void deleteState(Long id) {
		try {
			stateRepository.deleteById(id);
		} 
		catch (EmptyResultDataAccessException e) {
			throw new IdNotFoudException(MessageUtil.ID_NOT_FOUND);
		}
	}

}
