package com.example.food.model.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.food.model.dto.PaymentsFormDTO;
import com.example.food.model.mapper.PaymentFormMapper;
import com.example.food.model.repository.PaymentFormRepository;
import com.example.food.model.services.PaymentForm;

@Service
public class PaymentFormImpl implements PaymentForm {

	@Autowired
	private PaymentFormRepository paymentFormRepository;

	@Autowired
	private PaymentFormMapper mapper;

	@Override
	public List<PaymentsFormDTO> searchPayments() {
		var payments = paymentFormRepository.findAll();
		return mapper.toDTOList(payments);
	}

}
