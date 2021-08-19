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
@Table(name = "tb_order_item")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class OrderItem {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "quantity", nullable = false)
	private Integer quantity;

	@Column(name = "unitary_price", nullable = false)
	private BigDecimal unitaryPrice;

	@Column(name = "total_price", nullable = false)
	private BigDecimal totalPrice;

	@Column(name = "observation")
	private String observation;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Products products;

	public void calculateTotalPrice() {
//		unitaryPrice = unitaryPrice == null ? BigDecimal.ZERO : unitaryPrice;
//		quantity = quantity == null ? 0 : quantity;
		
		if(unitaryPrice == null) {
			unitaryPrice = BigDecimal.ZERO;
		}
		
		if(quantity == null) {
			quantity = 0;
		}
		
		setTotalPrice(unitaryPrice.multiply(new BigDecimal(quantity)));
	}

}
