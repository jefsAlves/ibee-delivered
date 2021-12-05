package com.example.food.infra.repository;

import com.example.food.model.entities.City;

public interface CityRepositoryCustomized {

	City searchName(String name);
}
