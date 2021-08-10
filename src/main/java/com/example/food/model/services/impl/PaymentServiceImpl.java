package com.example.food.model.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.food.model.dto.PaymentDTO;
import com.example.food.model.entities.Payments;
import com.example.food.model.exceptions.IdNotFoudException;
import com.example.food.model.mapper.PaymentMapper;
import com.example.food.model.repository.PaymentRepository;
import com.example.food.model.services.PaymentService;
import com.example.food.model.util.MessageUtil;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private PaymentMapper mapper;

	@Override
	public Payments search(Long paymentId) {
		var payment = paymentRepository.findById(paymentId);
		return payment.orElseThrow(() -> new IdNotFoudException(MessageUtil.ID_NOT_FOUND));
	}

	public PaymentDTO searchPayment(Long paymentId) {
		Optional<Payments> payments = paymentRepository.findById(paymentId);
		payments.orElseThrow(() -> new IdNotFoudException(MessageUtil.ID_NOT_FOUND));
		return mapper.toDTO(payments);
	}

	@Override
	public List<PaymentDTO> listPayments() {
		var payments = paymentRepository.findAll();
		return mapper.toDTOList(payments);
	}

}
