package com.example.food.infra.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.example.food.model.entities.City;

@Repository
public class CityRepositoryCustomizedImpl implements CityRepositoryCustomized {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public City searchName(String name) {
		String jpql = "FROM City WHERE name like :name ";

		return manager.createQuery(jpql, City.class).setParameter("name", "%" + name + "%").getSingleResult();
	}

}
