package com.org.mortgageapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.org.mortgageapp.constant.UserUtility;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ErrorResponse> customException(CustomException ex) {

		ErrorResponse errorResponse = new ErrorResponse();

		errorResponse.setMessage(ex.getMessage());
		errorResponse.setStatus(UserUtility.USER_NOT_EXIST_STATUS);

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(UserNotEligible.class)
	public ResponseEntity<ErrorResponse> userNotEligible(UserNotEligible ex) {

		ErrorResponse errorResponse = new ErrorResponse();

		errorResponse.setMessage(ex.getMessage());
		errorResponse.setStatus(UserUtility.USER_NOT_ELIGIBLE_STATUS);

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(PropertyDetailNotSaved.class)
	public ResponseEntity<ErrorResponse> propertyDetailNotSaved(PropertyDetailNotSaved ex) {

		ErrorResponse errorResponse = new ErrorResponse();

		errorResponse.setMessage(ex.getMessage());
		errorResponse.setStatus(UserUtility.PROPERTY_DETAIL_NOT_SAVED_STATUS);

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(NoDataException.class)
	public ResponseEntity<ErrorResponse> userNotLoginException(NoDataException ex) {

		ErrorResponse errorResponse = new ErrorResponse();

		errorResponse.setMessage(ex.getMessage());
		errorResponse.setStatus(UserUtility.NO_DATA_FOUND_STATUS);

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(PaymentException.class)
	public ResponseEntity<ErrorStatus> paymentException(PaymentException ex) {

		ErrorStatus errorStatus = new ErrorStatus();

		errorStatus.setStatuscode(700);
		errorStatus.setStatusmessage(com.org.mortgageapp.constant.UserUtility.NO_MORTGAGE_DETAIL_FOUND);
		return new ResponseEntity<>(errorStatus, HttpStatus.BAD_REQUEST);

	}

}