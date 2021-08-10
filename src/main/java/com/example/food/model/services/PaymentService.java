package com.example.food.model.services;

import java.util.List;

import com.example.food.model.dto.PaymentDTO;
import com.example.food.model.entities.Payments;

public interface PaymentService {

	Payments search(Long paymentId);

	PaymentDTO searchPayment(Long paymentId);

	List<PaymentDTO> listPayments();

}
