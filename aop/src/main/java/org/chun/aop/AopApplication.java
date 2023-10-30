package org.chun.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.chun.aop.async.AsyncService;
import org.chun.aop.dispersion.DemoTask;
import org.chun.aop.validator.Role;
import org.chun.aop.validator.ValidatorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
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
	private final ValidatorService validatorService;
	private final DemoTask demoTask;

	@Override
	public void run(String... args) throws Exception {
		log.info(" >>> run <<< " );
		CurrentUtil.threadInfo(this);
		demoTask.execute();
		asyncService.asyncBuilder();
		validatorService.printHelloWorld(new Role(1L, "1"));
	}

	@Bean
	ApplicationListener<ApplicationReadyEvent> readyEventApplicationListener() {
		CurrentUtil.threadInfo(this);
		return event -> log.info("Aop Start Success.");
	}

}
