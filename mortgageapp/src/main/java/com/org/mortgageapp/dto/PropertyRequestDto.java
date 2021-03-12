package com.org.mortgageapp.dto;

public class PropertyRequestDto {

	private String panCard;
	private double propertyValue;
	private String propertyType;
	private double initialDeposit;
	private int tenure;

	public String getPanCard() {
		return panCard;
	}

	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}

	public double getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(double propertyValue) {
		this.propertyValue = propertyValue;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public double getInitialDeposit() {
		return initialDeposit;
	}

	public void setInitialDeposit(double initialDeposit) {
		this.initialDeposit = initialDeposit;
	}

	public int getTenure() {
		return tenure;
	}

	public void setTenure(int tenure) {
		this.tenure = tenure;
	}

}
