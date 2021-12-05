package com.example.food.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.food.model.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
