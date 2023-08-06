package org.chun.aop;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CurrentUtil {
	public static <T> void threadInfo(T param) {

//		Class<T> clazz = param.getClass();
//
//		Thread.currentThread().getStackTrace()
	}

	public static void threadInfo() {
		log.info("====== Thread Info =====================================================================");
		Thread thread = Thread.currentThread();
		log.info("Thread Name: {}, Id: {}, group:{}", thread.getName(), thread.getId(), thread.getThreadGroup());
		log.info("Active Count: {}", Thread.activeCount());
		log.info("========================================================================================");
	}


}
