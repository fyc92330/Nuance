package org.chun.aop.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RoleTypeDemoService {

	@Cacheable(cacheNames = "", key = "#p0")
	@RolePermission(type = RoleType.USER, param = "#p0.getRoleType()",message = "error")
	public void printHelloWorld(Role role){
		log.info("Hello World !!");
	}
}
