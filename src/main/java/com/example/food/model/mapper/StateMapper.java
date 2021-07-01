package com.example.food.model.mapper;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.food.model.dto.StateDTO;
import com.example.food.model.entities.State;

@Component
public class StateMapper {

	@Autowired
	private ModelMapper modelMapper;

	public StateDTO toDTO(State state) {
		return modelMapper.map(state, StateDTO.class);
	}

	public State toEntity(StateDTO stateDTO) {
		return modelMapper.map(stateDTO, State.class);
	}
	
	public List<StateDTO> toDTOList(List<State> state) {
		return modelMapper.map(state, new TypeToken<List<State>>() {}.getType());
	}

	public List<State> toEntityList(List<StateDTO> stateDTO) {
		return modelMapper.map(stateDTO, new TypeToken<List<StateDTO>>() {
		}.getType());
	}
	
	public void copyProperties(StateDTO stateDTO, State state) {
		modelMapper.map(stateDTO, state);
	}
	
	public StateDTO toDTO(Optional<State> state) {
		return modelMapper.map(state, StateDTO.class);
	}

}
