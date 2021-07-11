package com.example.food.config.integration;

import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.food.model.dto.CityDTO;

@Service
public class SendCity {

	@Autowired
	@Qualifier("cityProducer")
	private KafkaProducer<String, CityDTO> kafkaProducer;

	@Value("${spring.cloud.stream.bindings.topic-city.destination}")
	private String topic;

	public void sendMessage(@RequestBody CityDTO cityDTO) throws InterruptedException, ExecutionException {
		ProducerRecord<String, CityDTO> record = new ProducerRecord<>(topic, cityDTO);
		kafkaProducer.send(record);
	}
}
