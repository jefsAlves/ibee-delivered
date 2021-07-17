package com.example.food.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.food.model.entities.Groups;

public interface GroupsRepository extends JpaRepository<Groups, Long> {

}
