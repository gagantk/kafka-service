package com.gagantk.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.gagantk.model.ConsumerResponse;

@FeignClient(name="consumer-service")
public interface ConsumerCallerProxy {
	
	@GetMapping("/kafka/getcount")
	public ConsumerResponse getCount();

}
