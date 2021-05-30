package com.example.food.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.food.model.entities.State;

public interface StateRepository extends JpaRepository<State, Long> {

	State findByName(String name);
}
