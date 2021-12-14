package com.example.food.infra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.food.model.entities.User;

public interface UsersRepository extends JpaRepository<User, Long> {

	User findByUser(String user);

	@Query(value = "FROM Restaurant r WHERE r.id = :restaurantId")
	List<User> searchById(@Param("restaurantId") Long restaurantId);

}
