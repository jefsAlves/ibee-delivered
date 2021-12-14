package com.example.food.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.food.api.controller.groups.StateGroups.StateId;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "tb_state")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class State {

	@NotNull(groups = StateId.class)
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(name = "name", nullable = false)
	private String name;

}
