package com.gagantk.consumer.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gagantk.model.Message;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

	private ObjectMapper mapper = new ObjectMapper();
	private int count = 0;

	@Override
	@KafkaListener(topics = "${message.topic.name}", groupId = "${kafka.consumer.group.id}", containerFactory = "kafkaListenerContainerFactory")
	public void listenMessage(Message message) throws JsonProcessingException {
		log.info("Consumed message: {}", mapper.writeValueAsString(message));
		count++;
		log.info(String.valueOf(count));
	}

	@Override
	public int getMessagesConsumedCount() {
		return count;
	}

}
