package com.example.food.model.entities;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Address {

	private String addressCep;
	private String addressStreet;
	private String addressNumber;
	private String addressComplement;
	private String addressNeighborhood;

}
