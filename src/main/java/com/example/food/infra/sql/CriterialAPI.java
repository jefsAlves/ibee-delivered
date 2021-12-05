package com.example.food.infra.sql;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.example.food.model.entities.Restaurant;

@Repository
public class CriterialAPI {

	@PersistenceContext
	private EntityManager manager;

	public List<Restaurant> findRestaurants(String name, BigDecimal freigthRateInitial, BigDecimal freigthRateFinally) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Restaurant> createCriteria = builder.createQuery(Restaurant.class);
		Root<Restaurant> root = createCriteria.from(Restaurant.class);

		var predicate = new ArrayList<Predicate>();

		if (StringUtils.hasText(name)) {
			predicate.add(builder.like(root.get("name"), "%" + name + "%"));
		}

		if (freigthRateInitial != null) {
			predicate.add(builder.greaterThanOrEqualTo(root.get("freigthRateInitial"), freigthRateInitial));
		}

		if (freigthRateFinally != null) {
			predicate.add(builder.lessThanOrEqualTo(root.get("freigthRateFinally"), freigthRateFinally));
		}
		
		createCriteria.where(predicate.toArray(new Predicate[0]));

		TypedQuery<Restaurant> query = manager.createQuery(createCriteria);
		return query.getResultList();
	}

}
