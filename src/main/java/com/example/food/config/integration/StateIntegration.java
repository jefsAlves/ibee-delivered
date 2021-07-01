package com.example.food.config.integration;

import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.food.model.dto.StateDTO;

@Service
public class StateIntegration {

	@Autowired
	private KafkaProducer<String, StateDTO> kafkaProducer;

	@Value("${spring.cloud.stream.bindings.topic.destination}")
	private String topic;

	public void sendMessage(StateDTO stateDTO) throws InterruptedException, ExecutionException {
		stateDTO.setName("Teste");
		ProducerRecord<String, StateDTO> record = new ProducerRecord<String, StateDTO>(topic, stateDTO);
		kafkaProducer.send(record);
	}
}
