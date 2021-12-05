package com.example.food.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Component;

import com.example.food.api.dto.KitchenDTO;

@Component
public class KafkaConfigKitchen {

	@Value("${spring.cloud.stream.kafka.binder.brokers}")
	private String bootstrapServer;

	@Value("${spring.kafka.producer.group-id}")
	private String groupId;

	@Bean("kitchenProducer")
	public KafkaProducer<String, KitchenDTO> kafkaProducer() {
		return new KafkaProducer<>(configsProducer());
	}

	@Bean("kitchenKafkaConfig")
	public Map<String, Object> configsProducer() {
		Map<String, Object> configs = new HashMap<>();
		configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return configs;
	}

}
