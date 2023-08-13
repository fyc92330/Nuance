package org.chun.aop.validator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
public class RolePermissionValidator implements ConstraintValidator<RolePermission, RoleTypeDemoService> {
	@SneakyThrows
	@Override
	public boolean isValid(RoleTypeDemoService service, ConstraintValidatorContext context) {
		log.warn("Validator");
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		String roleType = (String) session.getAttribute("roleType");
		RoleType typeEnum = RoleType.getInstance(roleType);

		Method value = service.getClass().getMethod("printHelloWorld");

		RolePermission annotation = value.getAnnotation(RolePermission.class);
		boolean isValidType = Arrays.stream(annotation.type())
				.anyMatch(e -> e == typeEnum);

		if(!isValidType){
			throw new RuntimeException(annotation.message());
		}

		return isValidType;
	}
}
