package com.deutsche.tradeservice.validator;

import org.springframework.beans.factory.annotation.Autowired;

import com.deutsche.tradeservice.constraint.TradeVersionLowerConstraint;
import com.deutsche.tradeservice.data.TradeUI;
import com.deutsche.tradeservice.service.TradeService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TradeVersionLowerValidator implements ConstraintValidator<TradeVersionLowerConstraint, TradeUI> {

	@Autowired
	TradeService trService;

	@Override
	public void initialize(TradeVersionLowerConstraint tradeVersionLowerConstraint) {
	}

	@Override
	public boolean isValid(TradeUI trade, ConstraintValidatorContext context) {
		return trService.isTradeVersionCorrect(trade);
	}
}
