package com.example.food.model.mapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.food.model.dto.OrderDTO;
import com.example.food.model.dto.OrderNewDTO;
import com.example.food.model.entities.Order;

@Component
public class OrderMapper {

	@Autowired
	private ModelMapper modelMapper;

	public OrderDTO toDTO(Order order) {
		return modelMapper.map(order, OrderDTO.class);
	}

	public Order toEntity(OrderDTO orderDTO) {
		return modelMapper.map(orderDTO, Order.class);
	}

	public List<OrderDTO> toDTOList(List<Order> order) {
		return modelMapper.map(order, new TypeToken<List<Order>>() {}.getType());
	}

	public List<Order> toDTOEntity(List<OrderDTO> orderDTO) {
		return modelMapper.map(orderDTO, new TypeToken<List<OrderDTO>>() {}.getType());
	}

	public OrderDTO toDTO(Optional<Order> order) {
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setId(order.get().getId());
		orderDTO.setSubTotal(order.get().getSubTotal());
		orderDTO.setFreigthRate(order.get().getFreigthRate());
		orderDTO.setTotalValue(order.get().getSubTotal());
		orderDTO.setStatus(order.get().getOrderStatus().toString());
		orderDTO.setCreateDate(OffsetDateTime.now());
		orderDTO.setRestaurant(RestaurantMapper.toDTOOptional(order.get().getRestaurant()));
		orderDTO.setUsers(UserMapper.toDTOOptional(order.get().getUsers()));
		orderDTO.setPayments(PaymentMapper.toDTOOptional(order.get().getPayments()));
		orderDTO.setAddress(AddressMapper.toDTOOptional(order.get().getAddress()));
		return orderDTO;
	}

	public Order toEntity(OrderNewDTO orderNewDTO) {
		var order = new Order();
		order.setCreateDate(LocalDateTime.now());
		order.setFreigthRate(new BigDecimal(3.45));
		return modelMapper.map(orderNewDTO, Order.class);
	}

}
