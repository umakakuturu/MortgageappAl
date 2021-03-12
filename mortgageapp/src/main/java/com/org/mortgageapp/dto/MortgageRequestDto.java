package com.org.mortgageapp.dto;

public class MortgageRequestDto {

	private String panCard;
	private String paymentType;
	//for these all generate setter nd getter
	public String getPanCard() {
		return panCard;
	}

	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
}
