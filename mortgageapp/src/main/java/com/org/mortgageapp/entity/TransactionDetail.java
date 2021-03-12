package com.org.mortgageapp.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity

@Table(name = "TRANSACTION_DETAIL")
public class TransactionDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long transactionId;

	@Column(name = "CUSTOMER_ID")
	private String customerId;

	@Column(name = "EMI")
	private double emi;

	@Column(name = "DATE")
	private LocalDate date;

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public double getEmi() {
		return emi;
	}

	public void setEmi(double emi) {
		this.emi = emi;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

}
