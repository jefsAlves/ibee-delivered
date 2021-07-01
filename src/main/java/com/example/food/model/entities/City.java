package com.example.food.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.example.food.model.controller.groups.StateGroups.StateId;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "tb_city")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class City {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(name = "name", nullable = false)
	private String name;

	@Valid
	@ConvertGroup(from = Default.class, to = StateId.class)
//	@NotNullFS
//	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "state_id", nullable = false)
	private State state;
}
