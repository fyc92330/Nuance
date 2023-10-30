package org.chun.aop.dispersion;

import java.util.concurrent.Callable;

public class AutomaticLock implements AutoCloseable {

	private final String lockKey;

	private AutomaticLock(String key){
		this.lockKey = key;
	}

	public static AutomaticLock getInstance(String key){
		return new AutomaticLock(key);
	}

	public void exec(Runnable runnable){
		if(!GlobalLockCache.lock(lockKey)){
			return;
		}

		runnable.run();
	}

	public <T> T exec(Callable<T> callable) throws Exception {
		if(!GlobalLockCache.lock(lockKey)){
			return null;
		}

		return callable.call();
	}

	@Override
	public void close() throws Exception {
		GlobalLockCache.release(lockKey);
	}

}
