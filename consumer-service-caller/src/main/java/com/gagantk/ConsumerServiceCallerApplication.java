package com.gagantk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ConsumerServiceCallerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerServiceCallerApplication.class, args);
	}

}
