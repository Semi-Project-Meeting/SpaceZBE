package com.spaceZ.payment;

public interface PaymentDAO {

	public String getAccessToken();
	
	public PaymentInfo getPaymentInfo(String accessToke);
	
	public int verifyPayInfo();
	
}
