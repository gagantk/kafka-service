package com.gagantk.producer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfig {
	
	@Value(value = "${kafka.server.address}")
	private String kafkaServerAddress;
	
	@Value(value = "${message.topic.name}")
	private String topicName;
	
	@Bean
	public KafkaAdmin kafkaAdmin() {
		Map<String, Object> config = new HashMap<>();
		config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServerAddress);
		return new KafkaAdmin(config);
	}
	
	@Bean
	public NewTopic topic1() {
		return TopicBuilder.name(topicName).partitions(2).build();
	}

}
