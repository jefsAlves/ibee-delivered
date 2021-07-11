package com.example.food.model.services;

import java.util.List;

import com.example.food.model.dto.PaymentsFormDTO;

public interface PaymentForm {

	List<PaymentsFormDTO> searchPayments();
}
