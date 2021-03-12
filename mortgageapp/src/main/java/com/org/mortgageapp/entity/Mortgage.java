package com.org.mortgageapp.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity

public class Mortgage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long mortgageId;

	private String customerId;
	private String password;
	private String savingsAccountNumber;
	private double savingsAccountBalance;
	private String mortgageAccountNumber;
	private double mortgageAccountBalance;
	private double emi;
	private String paymentType;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public long getMortgageId() {
		return mortgageId;
	}

	public void setMortgageId(long mortgageId) {
		this.mortgageId = mortgageId;
	}

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

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
