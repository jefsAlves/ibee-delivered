package com.example.food.config.integration;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.food.model.dto.RestaurantDTO;

@Component
public class SendRestaurant {

	@Autowired
	@Qualifier("restaurantProducer")
	private KafkaProducer<String, RestaurantDTO> kafkaProducer;

	@Value("${spring.cloud.stream.bindings.topic-restaurant.destination}")
	private String topic;

	public void sendMessage(@RequestBody RestaurantDTO restaurantDTO) {
		ProducerRecord<String, RestaurantDTO> record = new ProducerRecord<String, RestaurantDTO>(topic, restaurantDTO);
		kafkaProducer.send(record);
	}

}
