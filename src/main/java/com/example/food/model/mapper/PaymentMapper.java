package com.example.food.model.mapper;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.food.model.dto.PaymentDTO;
import com.example.food.model.dto.PaymentsDTO;
import com.example.food.model.entities.Payment;

@Component
public class PaymentMapper {

	@Autowired
	private ModelMapper modelMapper;

	public PaymentDTO toDTO(Payment payments) {
		return modelMapper.map(payments, PaymentDTO.class);
	}
	
	public PaymentsDTO toDTOEntity(Payment payment) {
		return modelMapper.map(payment, PaymentsDTO.class);
	}

	public Payment toEntity(PaymentDTO paymentsDTO) {
		return modelMapper.map(paymentsDTO, Payment.class);
	}

	public List<PaymentDTO> toDTOList(List<Payment> payments) {
		return modelMapper.map(payments, new TypeToken<List<Payment>> () {}.getType());
	}
	
	public List<Payment> toEntityList(List<PaymentDTO> paymentsDTO) {
		return modelMapper.map(paymentsDTO, new TypeToken<List<Payment>>() {}.getType());
	}
	
	public PaymentDTO toDTO(Optional<Payment> payments) {
		return modelMapper.map(payments, PaymentDTO.class);
	}
	
	public static PaymentDTO toDTOOptional(Payment payment) {
		PaymentDTO paymentDTO = new PaymentDTO();
		paymentDTO.setId(payment.getId());
		paymentDTO.setDescription(payment.getDescription());
		return paymentDTO;
	}
	
}
