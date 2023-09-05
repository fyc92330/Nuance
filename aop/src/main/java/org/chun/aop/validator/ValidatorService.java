package org.chun.aop.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ValidatorService {

	@RolePermission(type = RoleType.USER, param = "#p0.getRoleType()",message = "error")
	public void printHelloWorld(Role role){
		log.info("Hello World !!");
	}
}
