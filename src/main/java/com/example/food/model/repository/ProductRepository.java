package com.example.food.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.food.model.entities.Products;
import com.example.food.model.entities.Restaurant;

public interface ProductRepository extends JpaRepository<Products, Long> {

	List<Products> findByRestaurant(Restaurant restaurant);

	@Query("FROM Products p WHERE p.restaurant.id = :restaurantId AND p.id = :productId")
	Optional<Products> findById(@Param("restaurantId") Long restaurantId, @Param("productId") Long productId);

}
