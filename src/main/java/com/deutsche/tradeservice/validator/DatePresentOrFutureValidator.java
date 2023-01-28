package com.deutsche.tradeservice.validator;

import org.springframework.beans.factory.annotation.Autowired;

import com.deutsche.tradeservice.constraint.DatePresentOrFutureConstraint;
import com.deutsche.tradeservice.service.TradeService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DatePresentOrFutureValidator implements ConstraintValidator<DatePresentOrFutureConstraint, String> {

	@Autowired
	TradeService trService;

	public final void initialize(final DatePresentOrFutureValidator annotation) {
	}

	@Override
	public final boolean isValid(String maturityDateAsString, ConstraintValidatorContext context) {

		return trService.isMaturityDateCorrect(maturityDateAsString);

	}

}