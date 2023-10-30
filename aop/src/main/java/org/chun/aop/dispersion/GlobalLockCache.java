package org.chun.aop.dispersion;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GlobalLockCache {

	private static final Map<String, LocalDateTime> locks = new ConcurrentHashMap<>();

	public static boolean isExists(String key){
		return locks.containsKey(key);
	}

	public static boolean lock(String key){
		if(isExists(key)){
			return false;
		}

		locks.put(key, LocalDateTime.now());
		return true;
	}

	public static boolean release(String key){
		if(isExists(key)){
			locks.remove(key);
			return true;
		}

		return false;
	}
}
