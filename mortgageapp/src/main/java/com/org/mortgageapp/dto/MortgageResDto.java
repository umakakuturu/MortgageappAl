package com.org.mortgageapp.dto;

public class MortgageResDto {

	private int statusCode;
	private String message;
	private String customerId;
	private String savingsAccountNumber;
	private double savingsAccountBalance;
	private String mortgageAccountNumber;
	private double mortgageAccountBalance;
	private double emi;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getSavingsAccountNumber() {
		return savingsAccountNumber;
	}

	public void setSavingsAccountNumber(String savingsAccountNumber) {
		this.savingsAccountNumber = savingsAccountNumber;
	}

	public double getSavingsAccountBalance() {
		return savingsAccountBalance;
	}

	public void setSavingsAccountBalance(double savingsAccountBalance) {
		this.savingsAccountBalance = savingsAccountBalance;
	}

	public String getMortgageAccountNumber() {
		return mortgageAccountNumber;
	}

	public void setMortgageAccountNumber(String mortgageAccountNumber) {
		this.mortgageAccountNumber = mortgageAccountNumber;
	}

	public double getMortgageAccountBalance() {
		return mortgageAccountBalance;
	}

	public void setMortgageAccountBalance(double mortgageAccountBalance) {
		this.mortgageAccountBalance = mortgageAccountBalance;
	}

	public double getEmi() {
		return emi;
	}

	public void setEmi(double emi) {
		this.emi = emi;
	}

}
