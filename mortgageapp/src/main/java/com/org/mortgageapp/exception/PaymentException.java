package com.org.mortgageapp.exception;

public class PaymentException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final String errorMessage;
	private final int errorCode;

	public String getErrorMessage() {
		return errorMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public PaymentException(String errorMessage, int errorCode) {
		super();
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
	}

}