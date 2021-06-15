package com.gagantk.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gagantk.producer.service.KafkaProducerService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/kafka")
public class KafkaProducerController {

	@Autowired
	KafkaProducerService kafkaProducerService;

	@Value(value = "${message.topic.name}")
	private String topicName;

	@Operation(summary = "Publish random messages to Kafka continuously")
	@GetMapping(value = "/publish")
	public String produceMessage() {
		kafkaProducerService.sendContinuousMessages();
		return "Started publishing messages continuously to topic: " + topicName;
	}

}
