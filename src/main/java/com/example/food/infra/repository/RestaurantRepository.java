package com.example.food.infra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.food.model.entities.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

//	@Query("FROM Restaurant WHERE name like %:findName%")
	Restaurant searchNameRestaurant(@Param("findName") String name);

	@Query("FROM Restaurant r left join fetch r.kitchen ")
	List<Restaurant> findAll();

	Restaurant findByName(String name);
}
