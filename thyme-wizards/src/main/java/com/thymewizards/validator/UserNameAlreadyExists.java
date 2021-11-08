package com.thymewizards.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserNameAlreadyExistsValidator.class) 
public @interface UserNameAlreadyExists {
	String message() default "{UserNameAlreadyExists}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
