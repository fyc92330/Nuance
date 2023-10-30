package org.chun.aop.aspect;

import io.micrometer.common.util.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.chun.aop.dispersion.AutomaticLock;
import org.chun.aop.dispersion.DispersionLock;
import org.chun.aop.dispersion.TaskProcessor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class DispersionLockAspect {

	@Pointcut("within(org.chun.aop.dispersion..*)")
	public void withinDispersion() {

	}

	@Pointcut("@within(org.chun.aop.dispersion.DispersionLock) || @annotation(org.chun.aop.dispersion.DispersionLock)")
	public void dispersionLock() {

	}

	@Pointcut("withinDispersion() && dispersionLock()")
	public void dispersionLockAspectPointcut() {

	}

	@Around("dispersionLockAspectPointcut()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {

		DispersionLock dispersionLock = pjp.getTarget().getClass().getAnnotation(DispersionLock.class);
		if(dispersionLock.lock()){

			String key = StringUtils.isBlank(dispersionLock.key())
					? pjp.getTarget().getClass().getSimpleName()
					: dispersionLock.key();

			try(AutomaticLock lock = AutomaticLock.getInstance(key)){
				assert pjp.getTarget() instanceof TaskProcessor;
				lock.exec(((TaskProcessor) pjp.getTarget())::execute);
			}
		}

		return pjp.proceed();
	}

}
