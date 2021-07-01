package com.example.food.model.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "tb_restaurant")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurant {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(name = "name", nullable = false)
	private String name;

	@DecimalMin("1")
	@Column(name = "freigth_rate", nullable = false)
	private BigDecimal freigthRate;

	@JsonIgnore
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime createDate;

	@JsonIgnore
	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime updateDate;

//	@JsonIgnoreProperties(value = "name", allowGetters = true)
	@Valid
//	@ConvertGroup(from = Default.class, to = KitchenId.class)
//	@NotNull
//	@JsonIgnore
//	@JsonIgnoreProperties("hibernateLazyInitializer")
	@ManyToOne
	@JoinColumn(name = "kitchen_id")
	private Kitchen kitchen;

	@JsonIgnore
	@Embedded
	private Address address;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "tb_restaurant_payment", joinColumns = @JoinColumn(name = "restaurant_id"), inverseJoinColumns = @JoinColumn(name = "payment_id"))
	private Set<Payments> payments;

	@JsonIgnore
	@OneToMany(mappedBy = "restaurant")
	private List<Products> products;

}
