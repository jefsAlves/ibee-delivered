package com.example.food.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.food.model.entities.Permissions;

public interface PermissionsRepository extends JpaRepository<Permissions, Long> {

}
