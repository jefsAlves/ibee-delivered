package com.example.food.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.food.model.entities.City;

public interface CityRepository extends JpaRepository<City, Long>, CityRepositoryCustomized {

	City findByName(String name);
}
