package org.chun.guns;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class GunsApplication implements CommandLineRunner {

	@Autowired
	@Qualifier(value = "roleMap")
	private Map<String, String> roleMap;

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(GunsApplication.class, args);
	}

	@Bean
	ApplicationListener<ApplicationReadyEvent> readyEventApplicationListener() {
		log.info(">>> primary db user: {}", userService.getUser(1L));
		return event -> log.info("Ready. ");
	}

	@Override
	public void run(String... args) throws Exception {
		log.info(">>> start to run: ");
		roleMap.forEach((user,pwd) -> {
			userService.getUserByRole(user, pwd, 1L);
		});
	}
}
