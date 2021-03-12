package com.org.mortgageapp.dto;

public class ResponseDto {
	String message;
	int statusCode;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public ResponseDto(String message, int statusCode) {
		super();
		this.message = message;
		this.statusCode = statusCode;
	}

}