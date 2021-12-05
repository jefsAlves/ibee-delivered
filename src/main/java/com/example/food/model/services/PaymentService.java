package com.example.food.model.services;

import java.util.List;

import com.example.food.api.dto.PaymentDTO;
import com.example.food.model.entities.Payment;

public interface PaymentService {

	Payment search(Long paymentId);

	PaymentDTO searchPayment(Long paymentId);

	List<PaymentDTO> listPayments();

}
