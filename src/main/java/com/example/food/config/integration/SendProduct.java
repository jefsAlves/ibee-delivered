package com.example.food.config.integration;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.food.api.dto.ProductDTO;

@Component
public class SendProduct {

	@Autowired
	@Qualifier("productProducer")
	private KafkaProducer<String, ProductDTO> kafkaProducer;

	@Value("${spring.cloud.stream.bindings.topic-product.destination}")
	private String topic;

	public void sendMessage(@RequestBody ProductDTO productDTO) {
		ProducerRecord<String, ProductDTO> record = new ProducerRecord<>(topic, productDTO);
		kafkaProducer.send(record);
	}

}
