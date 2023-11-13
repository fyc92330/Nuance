package org.chun.eyes;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.session.Session;
import org.springframework.session.events.SessionCreatedEvent;

@Slf4j
@SpringBootApplication
public class EyesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EyesApplication.class, args);
	}

	@Bean
	ApplicationListener<SessionCreatedEvent> sessionCreatedEventListener() {
		log.info("START_1");
		return event -> {
			Session session = event.getSession();
			log.info("Session ID:{}, Create Time:{}", session.getId(), session.getCreationTime());
		};
	};

	@EventListener
	public void sessionCreatedEventListener(SessionCreatedEvent event){
		log.info("id:{}", event.getSessionId());
	}
}
