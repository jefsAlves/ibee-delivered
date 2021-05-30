package com.example.food.model.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "tb_products")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Products {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "price", nullable = false)
	private BigDecimal price;

	@Column(name = "active", nullable = false)
	private Boolean active;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "restaurant_id")
	private Restaurant restaurant;

}
