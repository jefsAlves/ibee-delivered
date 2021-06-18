package com.example.food.model.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.food.model.controller.groups.GroupRestaurant.KitchenId;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "tb_kitchen")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Kitchen {

	@NotNull(groups = KitchenId.class)
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(name = "name", nullable = false)
	private String name;

	@JsonIgnore
	@OneToMany(mappedBy = "kitchen")
	private List<Restaurant> restaurant;

}