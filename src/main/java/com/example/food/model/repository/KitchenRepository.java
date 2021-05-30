package com.example.food.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.food.model.entities.Kitchen;

public interface KitchenRepository extends JpaRepository<Kitchen, Long> {

	Kitchen findByName(String name);

}
