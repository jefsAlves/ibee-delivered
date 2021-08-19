package com.example.food.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.food.model.dto.OrderNewDTO;
import com.example.food.model.entities.Order;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		modelMapper.createTypeMap(OrderNewDTO.class, Order.class)
		.addMappings(src -> src.skip(Order::setId));

		return new ModelMapper();
	}
}
