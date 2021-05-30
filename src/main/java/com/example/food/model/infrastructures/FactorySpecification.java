package com.example.food.model.infrastructures;

import org.springframework.data.jpa.domain.Specification;

import com.example.food.model.entities.Restaurant;

public class FactorySpecification {

	public static Specification<Restaurant> findName(String name) {
		return (root, query, builder) -> builder.like(root.get("name"), "%" + name + "%");
	}

}
