package com.gagantk.producer.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface KafkaProducerService {
	
	void sendMessage() throws JsonProcessingException;

	void sendContinuousMessages();

}
