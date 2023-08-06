package org.chun.amqp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Component
public class AmqpService {

	@Value("${rabbitmq.routing-key.demo-queue}")
	private String demoQueueKey;
	@Value("${rabbitmq.routing-key.demo-map-queue}")
	private String demoMapQueueKey;

	private static final String DEMO_EXCHANGE_NAME = "DemoExchange";
	private static final String DEMO_QUEUE_NAME = "demoQueue";
	private static final String DEMO_MAP_QUEUE_NAME = "demoMapQueue";

	private final RabbitTemplate rabbitTemplate;

	public void sendMessage(Message message, boolean isString) {
		String routingKey = isString ? demoQueueKey : demoMapQueueKey;
		rabbitTemplate.send(DEMO_EXCHANGE_NAME, routingKey, message);
	}

	@RabbitListener(queues = "demoQueue")
	public void consumeMessage(String message) {
		log.info("QueueName: {}, message: {}", DEMO_QUEUE_NAME, message);
	}

	@RabbitListener(queues = "demoMapQueue")
	public void consumeMapMessage(Map<String, Object> messageMap) {
		log.info("QueueName: {}, message: {}", DEMO_MAP_QUEUE_NAME, messageMap);
	}
}
