package com.spaceZ.payment;

import com.spaceZ.reservation.ReservationVO;

public interface PaymentDAO {

	//아임포트의 accesstoken 받기
	public String getAccessToken();
	//결제 정보 조회 및 선결제하기
	public int getPaymentInfo(ReservationVO vo);
	//보증금 결제하기
	public int depositOK(ReservationVO vo);
	//후불 결제 예약
	public int reserve(ReservationVO vo);
	//결제 정보와 실제 정보와 일치여부 확인
	public int verifyPayInfo(ReservationVO vo);
	//예약 취소 시 환불
	public int refund(RefundVO vo);
	//구매번호
	public String getMercant_uid();
	
}
