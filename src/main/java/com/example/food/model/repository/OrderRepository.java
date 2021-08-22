package com.example.food.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.food.model.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query("FROM Order o join fetch o.restaurant r join fetch r.kitchen k join fetch o.users")
	List<Order> findAll();

	@Query("FROM Order o WHERE o.id = :id")
	Order search(@Param("id") Long id);
}
