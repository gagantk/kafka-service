package com.gagantk.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.gagantk.model.ConsumerResponse;

@FeignClient(name="consumer-service", url="${CONSUMER_SERVICE_URI:http://localhost}:9200")
public interface ConsumerCallerProxy {
	
	@GetMapping("/kafka/getcount")
	public ConsumerResponse getCount();

}
