package com.gagantk.consumer.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gagantk.consumer.model.ConsumerResponse;
import com.gagantk.model.Message;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

	private ObjectMapper mapper = new ObjectMapper();
	private int count = 0;

	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	private Environment environment;

	@Override
	@KafkaListener(topics = "${message.topic.name}", groupId = "${kafka.consumer.group.id}", containerFactory = "kafkaListenerContainerFactory")
	public void listenMessage(Message message) throws JsonProcessingException {
		log.info("Consumed message: {}", mapper.writeValueAsString(message));
		count++;
		log.info(String.valueOf(count));
	}

	@Override
	public int getMessagesConsumedCount(HttpServletRequest request) {
		List<ServiceInstance> instances = discoveryClient.getInstances("CONSUMER-SERVICE");
		if (request.getHeader("user-agent").startsWith("Java/")
				&& request.getHeader("host").split(":")[0].equals(instances.get(0).getHost())) {
			return count + getOtherInstanceMessagesCount(instances);
		} else {
			return count;
		}
	}

	private int getOtherInstanceMessagesCount(List<ServiceInstance> instances) {
		log.info("Size: {}", instances.size());
		int newCount = 0;
		for (ServiceInstance instance : instances) {
			log.info(instance.toString());
			log.info(environment.getProperty("local.server.port"));
			if (instance.getPort() != Integer.parseInt(environment.getProperty("local.server.port"))) {
				String url = "http://localhost:" + instance.getPort() + "/kafka/getcount";
				log.info(url);
				ResponseEntity<ConsumerResponse> responseEntity = new RestTemplate().getForEntity(url,
						ConsumerResponse.class);
				ConsumerResponse response = responseEntity.getBody();
				if (response != null) {
					newCount = response.getCount();
				}
				log.info("Updated count: {}", newCount);
			}
		}
		return newCount;
	}

}
