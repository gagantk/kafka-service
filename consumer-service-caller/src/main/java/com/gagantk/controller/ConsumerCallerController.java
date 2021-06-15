package com.gagantk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gagantk.model.ConsumerCallerResponse;
import com.gagantk.model.ConsumerResponse;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/kafka")
public class ConsumerCallerController {

	@Autowired
	private ConsumerCallerProxy proxy;

	@GetMapping(value = "/count")
	@Operation(summary = "Get aggregated count of message consumed from Kafka broker")
	public ConsumerCallerResponse getCount() {

		ConsumerResponse consumerResponse = proxy.getCount();
		ConsumerCallerResponse response = new ConsumerCallerResponse();
		response.setAggregatedCount(consumerResponse.getCount());
		return response;
	}

}
