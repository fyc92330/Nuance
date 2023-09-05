package org.chun.aop.aspect;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.chun.aop.mask.Mask;
import org.chun.aop.mask.Masks;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Map;

@Aspect
@Component
@RequiredArgsConstructor
public class MaskAspect {

	private final ObjectMapper mapper;

	@Pointcut("execution(* org.chun.aop.mask.*.*(..))")
	public void maskFolder() {

	}

	@Pointcut("@annotation(org.chun.aop.mask.Mask) || @annotation(org.chun.aop.mask.Masks)")
	public void maskAnnotation() {

	}

	@Pointcut("maskFolder() && maskAnnotation()")
	public void mask() {

	}

	@Around("mask()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Mask[] masks = this.getMaskFields(methodSignature.getMethod().getAnnotations());
		Object result = joinPoint.proceed();
		if (masks == null) {
			return result;
		}

		Class<?> returnType = methodSignature.getReturnType();
		Map<String, Object> resultMap = mapper.convertValue(result, new TypeReference<Map<String, Object>>() {
		});
		for (Mask mask : masks) {
			String key = mask.field();
			Object value = resultMap.get(key);
			if(value instanceof String){
				value = this.paddingText((String) value, mask);
			}
			resultMap.put(key, value);
		}

		return mapper.convertValue(resultMap, returnType);
	}


	private Mask[] getMaskFields(Annotation[] annotations) {
		Mask[] masks = null;
		for (Annotation annotation : annotations) {
			if (annotation instanceof Mask) {
				masks = new Mask[]{(Mask) annotation};
			} else if (annotation instanceof Masks) {
				masks = ((Masks) annotation).value();
			}
		}
		return masks;
	}

	private String paddingText(String value, Mask mask){
		int prefix = mask.prefixLength();
		int suffix = mask.suffixLength();
		String padding = mask.padding();
		int middle = value.length() - prefix - suffix;

		StringBuilder sb = new StringBuilder();
		for(char c : value.toCharArray()){
			if(prefix-- != 0){
				sb.append(c);
			}else if(middle-- != 0){
				sb.append(padding);
			}else{
				sb.append(c);
			}
		}

		return sb.toString();
	}
}
