package org.chun.amqp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class AmqpApplication {

	private final AmqpService amqpService;

	public static void main(String[] args) {
		SpringApplication.run(AmqpApplication.class, args);
	}

	// ApplicationReadyEvent 推送的消息不會被consumer接收到??
	// 會不會是因為Start的時候Consume的Bean會比Listener還早建立, 所以消耗了>>卻沒被Listener抓到
	@Bean
	ApplicationListener<ApplicationReadyEvent> readyEventApplicationListener(){
		String readyMessageContent = "Ready to start RabbitMQ. ";
		Message message = new Message(readyMessageContent.getBytes(StandardCharsets.UTF_8));
		amqpService.sendMessage(message, true);
		return event -> log.info("App Start Ready. Time: " + LocalDateTime.now());
	}

}
