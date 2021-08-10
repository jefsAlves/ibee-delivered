package com.example.food.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.food.model.entities.Payments;

public interface PaymentRepository extends JpaRepository<Payments, Long> {

}