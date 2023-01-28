package com.deutsche.tradeservice.constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.deutsche.tradeservice.validator.TradeVersionLowerValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.ANNOTATION_TYPE, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { TradeVersionLowerValidator.class })
@Documented
public @interface TradeVersionLowerConstraint {

	String message() default "Version of Trade is lower than the one existing in system.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}