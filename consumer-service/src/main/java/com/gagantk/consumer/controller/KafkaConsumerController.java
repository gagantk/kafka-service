package com.gagantk.consumer.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gagantk.consumer.model.ConsumerResponse;
import com.gagantk.consumer.service.KafkaConsumerService;

@RestController
@RequestMapping("/kafka")
public class KafkaConsumerController {

	@Autowired
	private KafkaConsumerService kafkaConsumerService;

	@Autowired
	private Environment environment;

	@GetMapping(value = "/getcount")
	public ConsumerResponse getMessage(HttpServletRequest request) {
		int count = kafkaConsumerService.getMessagesConsumedCount(request);
		String port = environment.getProperty("local.server.port");
		ConsumerResponse response = new ConsumerResponse();
		response.setCount(count);
		response.setPort(port);
		return response;
	}

}
