package com.gagantk;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import com.gagantk.model.ConsumerCallerResponse;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class HttpRequestTest {

	@LocalServerPort
	private int port;

	@Autowired
	TestRestTemplate restTemplate;

	//Initial response for /kafka/count is {aggregatedCount : 0}
	@Test
	void getCountShouldReturnZeroCountMessageInitially() {
		assertEquals(0, this.restTemplate
				.getForObject("http://localhost:" + port + "/kafka/count", ConsumerCallerResponse.class)
				.getAggregatedCount());
	}

}
