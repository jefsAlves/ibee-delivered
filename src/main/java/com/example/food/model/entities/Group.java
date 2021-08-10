package com.example.food.model.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "tb_group")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Group {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@JsonIgnore
	@ManyToMany
	@JoinTable(
	name = "tb_group_permission",
	joinColumns  = @JoinColumn(name = "group_id"),
	inverseJoinColumns = @JoinColumn(name = "permission_id"))
	private Set<Permissions> permissions;
	
	public void addPermission(Permissions permissions) {
		getPermissions().add(permissions);
	}
	
	public void removePermission(Permissions permissions) {
		getPermissions().remove(permissions);
	}

}
