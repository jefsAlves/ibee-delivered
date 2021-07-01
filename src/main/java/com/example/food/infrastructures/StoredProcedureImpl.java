package com.example.food.infrastructures;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import com.example.food.model.entities.Kitchen;
import com.example.food.model.entities.Restaurant;

public class StoredProcedureImpl {

	@PersistenceContext(unitName = "database")
	private EntityManager entityManeger;

	final static String PROCEDURE_NAME = "persistence_integration";

	public void proc(EntityManager entityManager) {
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(PROCEDURE_NAME)
				.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
				.registerStoredProcedureParameter(3, Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter(4, Restaurant.class, ParameterMode.IN)
				.registerStoredProcedureParameter(5, Kitchen.class, ParameterMode.IN);

		query.setParameter(1, 6);
		query.setParameter(2, "name");
		query.setParameter(3, 90);
		query.setParameter(4, new Restaurant());
		query.setParameter(5, new Kitchen());

		query.execute();

		query.getOutputParameterValue(5);

	}

}
