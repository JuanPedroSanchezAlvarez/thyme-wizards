package com.thymewizards.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailAlreadyExistsValidator.class) 
public @interface EmailAlreadyExists {
	String message() default "{EmailAlreadyExists}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
