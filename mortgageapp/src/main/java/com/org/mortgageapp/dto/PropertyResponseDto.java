package com.org.mortgageapp.dto;

import lombok.Getter;
import lombok.Setter;

public class PropertyResponseDto {

	private double emi;
	private String message;
	private int statusCode;

	public double getEmi() {
		return emi;
	}

	public void setEmi(double emi) {
		this.emi = emi;
	}

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

}
