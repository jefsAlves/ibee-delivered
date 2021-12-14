package com.example.food.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.food.model.entities.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {

}
