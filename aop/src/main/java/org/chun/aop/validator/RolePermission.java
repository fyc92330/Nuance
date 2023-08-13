package org.chun.aop.validator;


import jakarta.validation.Constraint;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RolePermissionValidator.class)
@Documented
public @interface RolePermission {

	@AliasFor("value")
	RoleType[] type() default {};

	@AliasFor("type")
	RoleType[] value() default {};

	String message() default "";

	String param();

}
