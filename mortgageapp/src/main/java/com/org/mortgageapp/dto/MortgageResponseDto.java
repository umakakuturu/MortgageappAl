package com.org.mortgageapp.dto;

public class MortgageResponseDto {

	private String customerId;
	private String password;
	private String savingsAccountNumber;
	private String mortgageAccountNumber;
	private double savingsAccountBalance;
	private double mortgageAccountBalance;
	private double emi;
	private String paymentType;
	private String message;
	private int statusCode;
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSavingsAccountNumber() {
		return savingsAccountNumber;
	}
	public void setSavingsAccountNumber(String savingsAccountNumber) {
		this.savingsAccountNumber = savingsAccountNumber;
	}
	public String getMortgageAccountNumber() {
		return mortgageAccountNumber;
	}
	public void setMortgageAccountNumber(String mortgageAccountNumber) {
		this.mortgageAccountNumber = mortgageAccountNumber;
	}
	public double getSavingsAccountBalance() {
		return savingsAccountBalance;
	}
	public void setSavingsAccountBalance(double savingsAccountBalance) {
		this.savingsAccountBalance = savingsAccountBalance;
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
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
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
