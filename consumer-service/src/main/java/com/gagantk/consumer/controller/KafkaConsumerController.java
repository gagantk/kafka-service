package com.gagantk.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gagantk.consumer.model.ConsumerResponse;
import com.gagantk.consumer.service.KafkaConsumerService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/kafka")
@Slf4j
public class KafkaConsumerController {

	@Autowired
	private KafkaConsumerService kafkaConsumerService;

	@Autowired
	private Environment environment;

	@GetMapping(value = "/getcount")
	@Operation(summary = "Get count of consumed messages from Kafka broker")
	public ConsumerResponse getMessage() {
		int count = kafkaConsumerService.getMessagesConsumedCount();
		String port = environment.getProperty("local.server.port");
		String host = environment.getProperty("HOSTNAME");
		ConsumerResponse response = new ConsumerResponse();
		response.setCount(count);
		response.setPort(port);
		response.setHost(host);
		return response;
	}

}
