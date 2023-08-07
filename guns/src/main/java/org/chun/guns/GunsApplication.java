package org.chun.guns;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class GunsApplication implements CommandLineRunner {

	private final DatabaseService databaseService;

	public static void main(String[] args) {
		SpringApplication.run(GunsApplication.class, args);
	}

	@Bean
	ApplicationListener<ApplicationReadyEvent> readyEventApplicationListener() {
		databaseService.initialize();
		return event -> log.info(" ---- DataSource Cluster Initialize. ----");
	}

	@Override
	public void run(String... args) throws Exception {
		log.info(" ---- Start Running ----");
		databaseService.getEachData();
	}
}
