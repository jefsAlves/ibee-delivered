package com.example.food.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class AddressDTO {

	@JsonProperty("cep")
	private String addressCep;

	@JsonProperty("street")
	private String addressStreet;

	@JsonProperty("number")
	private String addressNumber;

	@JsonProperty("complement")
	private String addressComplement;

	@JsonProperty("neighborhood")
	private String addressNeighborhood;

	@JsonProperty("cityDTO")
	private CityDTO city;

}
