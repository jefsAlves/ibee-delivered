package com.example.food.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.support.serializer.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.example.food.model.dto.StateDTO;

@Component
public class KafkaConfig {

	@Value("${spring.kafka.producer.bootstrap-servers}")
	private String bootstrapServer;

	@Value("${spring.kafka.producer.group-id}")
	private String consumerGroup;

	@Bean
	public KafkaProducer<String, StateDTO> kafkaProducer() {
		return new KafkaProducer<>(configsProducer());
	}

	@Bean
	public Map<String, Object> configsProducer() {
		Map<String, Object> configs = new HashMap<>();
		configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return configs;
	}
}