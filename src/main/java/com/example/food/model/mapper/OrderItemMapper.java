package com.example.food.model.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.food.model.dto.OrderItemDTO;
import com.example.food.model.entities.OrderItem;

import lombok.Getter;

@Getter
public class OrderItemMapper {

	@Autowired
	private ModelMapper modelMapper;

	public OrderItemDTO toDTO(OrderItem orderItem) {
		return modelMapper.map(orderItem, OrderItemDTO.class);
	}

	public OrderItem toEntity(OrderItemDTO orderItemDTO) {
		return modelMapper.map(orderItemDTO, OrderItem.class);
	}

	public List<OrderItemDTO> toDTOList(List<OrderItem> orderItem) {
		return modelMapper.map(orderItem, new TypeToken<List<OrderItem>>() {}.getType());
	}
	
	public List<OrderItem> toEntityList(List<OrderItemDTO> orderItemDTO) {
		return modelMapper.map(orderItemDTO, new TypeToken<List<OrderItemDTO>>() {}.getType());
	}
	
}
