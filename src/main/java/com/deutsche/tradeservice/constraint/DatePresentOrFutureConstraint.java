package com.deutsche.tradeservice.constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.deutsche.tradeservice.validator.DatePresentOrFutureValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;


@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DatePresentOrFutureValidator.class)
@Documented
public @interface DatePresentOrFutureConstraint {
    String message() default "Date has to be today or future date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}