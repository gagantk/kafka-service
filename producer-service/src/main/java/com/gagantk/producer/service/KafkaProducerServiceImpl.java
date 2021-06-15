package com.gagantk.producer.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gagantk.model.Message;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaProducerServiceImpl implements KafkaProducerService {

	@Value(value = "${message.topic.name}")
	private String topicName;

	@Autowired
	private KafkaTemplate<String, Message> kafkaTemplate;

	private ObjectMapper mapper = new ObjectMapper();

	@Override
	public void sendMessage() throws JsonProcessingException {
		Message message = generateMessage();
		kafkaTemplate.send(topicName, message);
		log.info("Sent message: {}", mapper.writeValueAsString(message));
	}

	@Override
	public void sendContinuousMessages() {
		new Thread(() -> {
			while (true) {
				try {
					sendMessage();
					Thread.sleep(500);
				} catch (JsonProcessingException e) {
					log.error("JsonProcessingException: {}", e);
					break;
				} catch (InterruptedException e) {
					log.warn("Interrupted {}", e);
					Thread.currentThread().interrupt();
				}
			}
		}).start();
	}

	private Message generateMessage() {
		Message newMessage = new Message();
		UUID uuid = UUID.randomUUID();
		newMessage.setId(uuid.toString());
		newMessage.setMsg("New Message " + uuid.toString().replace("-", ""));
		return newMessage;
	}

}
