package com.example.food.config.integration;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.food.api.dto.KitchenDTO;

@Component
public class SendKitchen {

	@Autowired
	@Qualifier("kitchenProducer")
	private KafkaProducer<String, KitchenDTO> kafkaProducer;

	@Value("${spring.cloud.stream.bindings.topic-kitchen.destination}")
	private String topic;

	public void sendMessage(@RequestBody KitchenDTO kitchenDTO) {
		ProducerRecord<String, KitchenDTO> record = new ProducerRecord<String, KitchenDTO>(topic, kitchenDTO);
		kafkaProducer.send(record);
	}

}
