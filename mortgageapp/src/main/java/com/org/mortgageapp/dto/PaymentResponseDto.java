package com.org.mortgageapp.dto;

public class PaymentResponseDto {

	private String statusMessage;
	private int statusCode;
	
	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public PaymentResponseDto(String statusMessage, int statusCode) {
		super();
		this.statusMessage = statusMessage;
		this.statusCode = statusCode;
	}

	
}
