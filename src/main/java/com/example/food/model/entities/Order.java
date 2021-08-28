package com.example.food.model.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.example.food.model.entities.enums.OrderStatus;
import com.example.food.model.exceptions.BusinessException;
import com.example.food.model.util.MessageUtil;

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
	
	@Column(name = "order_code", nullable = false)
	private String orderCode;

	@Column(name = "sub_total", nullable = false)
	private BigDecimal subTotal;

	@Column(name = "freigth_rate", nullable = false)
	private BigDecimal freigthRate;

	@Column(name = "total_value", nullable = false)
	private BigDecimal totalValue;

	@CreationTimestamp
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "payment_id", nullable = false)
	private Payment payments;

	@ManyToOne
	@JoinColumn(name = "restaurant_id", nullable = false)
	private Restaurant restaurant;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User users;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> orderItem;
	
	public void calculateTotal() {
		getOrderItem().forEach(OrderItem::calculateTotalPrice);
		
		subTotal = getOrderItem()
					.stream()
						.map(it -> it.getTotalPrice())
							.reduce(BigDecimal.ZERO, BigDecimal::add);

		totalValue = subTotal.add(this.freigthRate);
	}
	
	public void definedFreighRate() {
		setFreigthRate(getRestaurant().getFreigthRate());
	}

	public void addOrderItemToOrder() {
		getOrderItem().forEach(it -> it.setOrder(this));
	}
	
	public void initialStatus() {
		this.orderStatus = OrderStatus.CREATE;
	}
	
	public void confirmeted() {
		setOrderStatus(OrderStatus.CONFIRMED);
		setConfirmationDate(LocalDateTime.now());
	}
	
	public void delivered() {
		setOrderStatus(OrderStatus.DELIVERED);
		setDeliveredDate(LocalDateTime.now());
	}
	
	public void cancelled() {
		setOrderStatus(OrderStatus.CANCELLED);
		setCancelledDate(LocalDateTime.now());
	}
	
	private void setOrderStatus(OrderStatus orderStatus) {
		if(getOrderStatus().cannotBeAlterStatus(orderStatus)) {
			throw new BusinessException(MessageUtil.CANNOT_BE_ALTER_STATUS);
		}
		
		this.orderStatus = orderStatus;
	}
	
	@PrePersist
	public void createOrderCode() {
		orderCode = UUID.randomUUID().toString();
	}
	
}
