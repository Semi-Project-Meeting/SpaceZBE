package com.spaceZ.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spaceZ.reservation.ReservationVO;

@Repository
public class PaymentService implements PaymentDAO {

	private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);


	@Autowired
	PaymentDAO dao;
	
	public String getAccessToken() {
		logger.info("getAccessToken...");
		
		return dao.getAccessToken();
	}

	public int getPaymentInfo(ReservationVO vo) {
		logger.info("getAccessToken...");
		return dao.getPaymentInfo(vo);
	}
	
	public int depositOK(ReservationVO vo) {
		logger.info("depositOK...");
		return dao.depositOK(vo);
	}

	public int verifyPayInfo(ReservationVO vo) {
		logger.info("verifyPayInfo...");
		return dao.verifyPayInfo(vo);
	}

	public int refund(RefundVO vo) {
		logger.info("refund...");
		return dao.refund(vo);
	}

	public int reserve(ReservationVO vo) {
		logger.info("reserve...");
		return dao.reserve(vo);
	}

}
