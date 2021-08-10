package com.example.food.model.mapper;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.food.model.dto.PaymentDTO;
import com.example.food.model.entities.Payments;

@Component
public class PaymentMapper {

	@Autowired
	private ModelMapper modelMapper;

	public PaymentDTO toDTO(Payments payments) {
		return modelMapper.map(payments, PaymentDTO.class);
	}

	public Payments toEntity(PaymentDTO paymentsDTO) {
		return modelMapper.map(paymentsDTO, Payments.class);
	}

	public List<PaymentDTO> toDTOList(List<Payments> payments) {
		return modelMapper.map(payments, new TypeToken<List<Payments>> () {}.getType());
	}
	
	public List<Payments> toEntityList(List<PaymentDTO> paymentsDTO) {
		return modelMapper.map(paymentsDTO, new TypeToken<List<Payments>>() {}.getType());
	}
	
	public PaymentDTO toDTO(Optional<Payments> payments) {
		return modelMapper.map(payments, PaymentDTO.class);
	}
}
