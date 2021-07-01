package com.example.food.model.jasper;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SalesDaily {

	private Date date;
	private Long totalQuantity;
	private BigDecimal totalSales;
}
