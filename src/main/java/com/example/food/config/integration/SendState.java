package com.example.food.config.integration;

import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.food.model.dto.StateDTO;

@Service
public class SendState {

	@Autowired
	@Qualifier("stateProducer")
	private KafkaProducer<String, StateDTO> kafkaProducer;

	@Value("${spring.cloud.stream.bindings.topic-state.destination}")
	private String topic;

	public void sendMessage(@RequestBody StateDTO stateDTO) throws InterruptedException, ExecutionException {
		ProducerRecord<String, StateDTO> record = new ProducerRecord<String, StateDTO>(topic, stateDTO);
		kafkaProducer.send(record);
	}
}
