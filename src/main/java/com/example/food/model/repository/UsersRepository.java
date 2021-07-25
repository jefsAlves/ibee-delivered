package com.example.food.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.food.model.entities.User;

public interface UsersRepository extends JpaRepository<User, Long>{

	User findByUser(String user);
}
