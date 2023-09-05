package org.chun.aop.aspect;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.chun.aop.validator.RolePermission;
import org.chun.aop.validator.RoleType;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Aspect
@Component
public class RolePermissionAspect {

	private static final Pattern PARAM_VALUE_PATTERN = Pattern.compile("#p(\\d+)\\.(\\w+)\\(\\)");

	@Pointcut("execution(* org.chun.aop.validator.*.*(..))")
	public void validator() {

	}

	@SneakyThrows
	@Before("validator()")
	public void beforeByExpression(JoinPoint joinPoint) {
		log.info("RoleType Validator By Expression Start ====>");
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		RolePermission methodRole = method.getAnnotation(RolePermission.class);
		if (methodRole == null) return;

		String param = methodRole.param();
		ExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression(param);
		EvaluationContext context = new StandardEvaluationContext();
		Matcher matcher = PARAM_VALUE_PATTERN.matcher(param);
		if (matcher.matches()) {
			int index = Integer.parseInt(matcher.group(1));
			context.setVariable(String.format("p%d", index), joinPoint.getArgs()[index]);
		}

		String value = expression.getValue(context, String.class);
		RoleType roleType = RoleType.getInstance(value);
		log.info("value:{}, enum:{}", value, roleType);

		RoleType[] validRoles = methodRole.type();
		boolean invalidRole = Arrays.stream(validRoles)
				.noneMatch(e -> e == roleType);
		log.info("invalid:{}, validRole:{}, loginRole:{}", invalidRole, validRoles, roleType);
//		if (invalidRole) throw new RuntimeException(methodRole.message());
		log.info("====================================================================================");
	}

	@SneakyThrows
	@Before("validator()")
	public void beforeByReflect(JoinPoint joinPoint) {
		log.info("RoleType Validator By Reflect Start ====>");
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		RolePermission methodRole = method.getAnnotation(RolePermission.class);
		if (methodRole == null) return;

		String param = methodRole.param();
		Matcher matcher = PARAM_VALUE_PATTERN.matcher(param);
		if (matcher.matches()) {
			int index = Integer.parseInt(matcher.group(1));
			String methodName = matcher.group(2);

			// getValue
			Object[] args = joinPoint.getArgs();
			Class<?> paramClass = args[index].getClass();
			Method getter = paramClass.getMethod(methodName);
			String roleType = String.valueOf(getter.invoke(args[index]));
			log.info("index:{}, methodName:{}, roleTypeStr:{}", index, methodName, roleType);

			RoleType roleTypeEnum = RoleType.getInstance(roleType);
			RoleType[] validRoles = methodRole.type();
			boolean invalidRole = Arrays.stream(validRoles)
					.noneMatch(e -> e == roleTypeEnum);
			log.info("invalid:{}, validRole:{}, loginRole:{}", invalidRole, validRoles, roleTypeEnum);
//			if (invalidRole) throw new RuntimeException(methodRole.message());
			log.info("====================================================================================");
		}

	}
}
