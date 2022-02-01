package com.kostisgerakos.kafkatoolbackend.service;

import org.apache.avro.generic.GenericRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;


@Service
public final class ProducerService {
	private static final Logger logger = LoggerFactory.getLogger(ProducerService.class);

	private final KafkaTemplate<String, GenericRecord> kafkaTemplate;

	public ProducerService(KafkaTemplate<String, GenericRecord> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void sendMessage(String topic, GenericRecord value) {
		logger.info(String.format("Kafka Producer => Producing message: %s", value.toString()));
		
		ListenableFuture<SendResult<String, GenericRecord>> future = this.kafkaTemplate.send(topic, value);
		future.addCallback(new ListenableFutureCallback<>() {
			@Override
			public void onFailure(Throwable ex) {
				logger.info("Unable to send message=[ {} ] due to : {}", value.toString(), ex.getMessage());
			}

			@Override
			public void onSuccess(SendResult<String, GenericRecord> result) {
				logger.info("Sent message=[ {} ] with offset=[ {} ]", value.toString(), result.getRecordMetadata().offset());
			}
		});
	}
}