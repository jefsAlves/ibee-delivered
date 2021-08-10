package com.example.food.model.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.food.model.entities.enums.OrderStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "tb_order")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Order {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "sub_total", nullable = false)
	private BigDecimal subTotal;

	@Column(name = "freigth_rate", nullable = false)
	private BigDecimal freigthRate;

	@Column(name = "amount", nullable = false)
	private BigDecimal amount;

	@Column(name = "create_date", nullable = false)
	private LocalDateTime createDate;

	@Column(name = "confirmation_date")
	private LocalDateTime confirmationDate;

	@Column(name = "cancelled_date")
	private LocalDateTime cancelledDate;

	@Column(name = "delivered_date")
	private LocalDateTime deliveredDate;

	@Embedded
	private Address address;

	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;

	@ManyToOne
	@JoinColumn(name = "payment_id", nullable = false)
	private Payments payments;

	@ManyToOne
	@JoinColumn(name = "restaurant_id", nullable = false)
	private Restaurant restaurant;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User users;

	@OneToMany(mappedBy = "order")
	private List<OrderItem> orderItem;

}
