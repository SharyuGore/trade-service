package com.deutsche.tradeservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import lombok.Data;

@ControllerAdvice
public class TradeControllerAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDto> handleException(MethodArgumentNotValidException ex) {

		ErrorDto dto = new ErrorDto(HttpStatus.BAD_REQUEST, "Validation error");

		dto.setDetailedMessages(ex.getBindingResult().getAllErrors().stream()
				.map(err -> err.unwrap(ConstraintViolation.class)).map(err -> {
					Path propertyPath = err.getPropertyPath();
					if (!StringUtils.EMPTY.equals(propertyPath.toString())) {
						return String.format("'%s' %s", propertyPath, err.getMessage());
					} else {
						return String.format("%s", err.getMessage());
					}
				}).collect(Collectors.toList()));

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);

	}

	@Data
	public static class ErrorDto {

		private final int status;
		private final String error;
		private final String message;
		private List<String> detailedMessages;

		public ErrorDto(HttpStatus httpStatus, String message) {
			status = httpStatus.value();
			error = httpStatus.getReasonPhrase();
			this.message = message;
		}

	}

}