package org.chun.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class StarterAspect {

	@Around("execution(* org.chun.aop.AopApplication.*(..))")
	public Object aroundStarter(ProceedingJoinPoint joinPoint) throws Throwable {
		AopApplication.threadName();
		return joinPoint.proceed();
	}

	@Around("execution(* org.chun.aop.*.*(..))")
	public Object logThreadName(ProceedingJoinPoint joinPoint) throws Throwable {
		log.warn("log>>>");
		AopApplication.threadName();
		return joinPoint.proceed();
	}
}
