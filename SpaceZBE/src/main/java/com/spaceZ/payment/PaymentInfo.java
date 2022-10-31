package com.spaceZ.payment;

public class PaymentInfo {

	private int price;

	public PaymentInfo() {
	}
	
	public PaymentInfo(int price) {
		super();
		this.price = price;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "PaymentInfo [price=" + price + "]";
	}
	
}
