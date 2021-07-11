package com.example.food.model.mapper;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.food.model.dto.PaymentsFormDTO;
import com.example.food.model.entities.Payments;

@Component
public class PaymentFormMapper {

	@Autowired
	private ModelMapper modelMapper;

	public PaymentsFormDTO toDTO(Payments payments) {
		return modelMapper.map(payments, PaymentsFormDTO.class);
	}

	public Payments toEntity(PaymentsFormDTO paymentsDTO) {
		return modelMapper.map(paymentsDTO, Payments.class);
	}

	public List<PaymentsFormDTO> toDTOList(List<Payments> payments) {
		return modelMapper.map(payments, new TypeToken<List<Payments>> () {}.getType());
	}
	
	public List<PaymentsFormDTO> toEntityList(List<PaymentsFormDTO> paymentsDTO) {
		return modelMapper.map(paymentsDTO, new TypeToken<List<Payments>>() {}.getType());
	}
	
	public PaymentsFormDTO toDTO(Optional<Payments> payments) {
		return modelMapper.map(payments, PaymentsFormDTO.class);
	}
}
