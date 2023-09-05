package org.chun.aop.mask;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(value = Masks.class)
@Documented
public @interface Mask {
	String field() default "";

	int prefixLength() default 1;
	int suffixLength() default 1;
	String padding() default "*";
}
