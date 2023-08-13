package org.chun.aop;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CurrentUtil {
	public static <T> void threadInfo(T point) {
		log.info("====== Thread Active: {} ========================================================", Thread.activeCount());
		Thread thread = Thread.currentThread();
		log.info("Thread Name: {}, Id: {}, point:{}", thread.getName(), thread.getId(), point.getClass().getSimpleName());
	}


}
