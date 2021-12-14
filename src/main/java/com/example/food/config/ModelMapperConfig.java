package com.example.food.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.food.api.dto.OrderItemInputDTO;
import com.example.food.model.entities.OrderItem;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		modelMapper.createTypeMap(OrderItemInputDTO.class, OrderItem.class)
				   .addMappings(src -> src.skip(OrderItem::setId));
		return modelMapper;
	}
}
