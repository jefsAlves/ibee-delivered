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

import com.example.food.model.dto.ProductDTO;

@Component
public class KafkaConfigProduct {

	@Value("${spring.cloud.stream.kafka.binder.brokers}")
	private String bootStrapServer;

	@Value("${spring.kafka.producer.group-id}")
	private String consumerGroup;

	@Bean("productProducer")
	public KafkaProducer<String, ProductDTO> productKafka() {
		return new KafkaProducer<>(productConfig());
	}

	@Bean("productConfig")
	public Map<String, Object> productConfig() {
		Map<String, Object> config = new HashMap<>();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServer);
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return config;
	}

}
