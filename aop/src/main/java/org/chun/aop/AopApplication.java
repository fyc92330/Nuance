package org.chun.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.EnableAsync;

@Slf4j
@EnableAsync
@RequiredArgsConstructor
@SpringBootApplication
public class AopApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AopApplication.class, args);
	}

	private final AsyncService asyncService;

	@Override
	public void run(String... args) throws Exception {
		threadName();
		asyncService.asyncBuilder();
	}

	ApplicationListener<ApplicationReadyEvent> readyEventApplicationListener(){
		threadName();
		return event -> log.info("Aop Start Success.");
	}

	public static void threadName(){
		log.info(" Thread Name >>> {}", Thread.currentThread().getName());
	}
}
