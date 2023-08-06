package org.chun.aop.async;

import lombok.extern.slf4j.Slf4j;
import org.chun.aop.CurrentUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AsyncService {

	@Async
	public void asyncBuilder() {
		CurrentUtil.threadInfo(this);
	}
}
