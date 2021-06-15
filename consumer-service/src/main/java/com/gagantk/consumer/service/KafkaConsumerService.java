package com.gagantk.consumer.service;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gagantk.model.Message;

public interface KafkaConsumerService {

	void listenMessage(Message message) throws JsonProcessingException;

	int getMessagesConsumedCount(HttpServletRequest request);

}
