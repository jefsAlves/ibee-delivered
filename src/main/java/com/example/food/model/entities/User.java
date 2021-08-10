package com.example.food.model.entities;

import java.time.LocalDateTime;
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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "tb_users")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user", nullable = false)
	private String user;

	@Column(name = "password", nullable = false)
	private String password;

	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime createDate;

	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime updateDate;

	@JsonIgnore
	@ManyToMany
	@JoinTable(
	name = "tb_user_group",
	joinColumns = @JoinColumn(name = "user_id"),
	inverseJoinColumns = @JoinColumn(name = "group_id"))
	private Set<Group> groups;
	
	public boolean verifyPasswordCurrent(String passwordCurrent) {
		return password.equals(passwordCurrent);
	}
	
	public void addGroup(Group group) {
		getGroups().add(group);
	}
	
	public void removeGroup(Group group) {
		getGroups().remove(group);
	}
	
}
