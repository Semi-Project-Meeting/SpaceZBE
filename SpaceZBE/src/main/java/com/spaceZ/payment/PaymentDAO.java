package com.spaceZ.payment;

public interface PaymentDAO {

	//아임포트의 accesstoken 받기
	public String getAccessToken();
	//결제 정보 조회
	public PaymentInfo getPaymentInfo(String imp_uid);
	//결제 정보와 실제 정보와 일치여부 확인
	public int verifyPayInfo(String imp_uid, long space_id);
	//예약 취소 시 환불
	public int refund(RefundVO vo);
	
}
