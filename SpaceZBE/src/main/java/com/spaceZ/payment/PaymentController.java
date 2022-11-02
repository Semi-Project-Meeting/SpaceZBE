package com.spaceZ.payment;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spaceZ.reservation.ReservationVO;

/**
 * 결제시스텀
 */
@Controller
public class PaymentController {

	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

	@Autowired
	PaymentService service;

	@Autowired
	HttpSession session;

	// 결제 상세페이지 -> 창현님과 합칠 부분
	@RequestMapping(value = "/selectOne", method = RequestMethod.GET)
	public String pay(Locale locale, Model model) {

		// 주문번호 고유값 설정 위해, 난수생성 -> 이것은 공간 등록 시 생성되어 추가되어야 한다.
		char random_alphabet = (char) ((Math.random() * 26) + 97);
		// merchant_uid : 고유값 -> 프론트에서 받아서 결제완료 버튼 클릭 시, VO Data로 다시 넘어와야 함.
		String merchant_uid = String.valueOf(random_alphabet) + System.currentTimeMillis();
		logger.info("merchantid:{}", merchant_uid);

		model.addAttribute("merchant_uid", merchant_uid);

		return "payment/pg";
	}

	// 선결제, 결제완료 버튼 클릭 -> 현민님과 합칠 부분
	@RequestMapping(value = "/payOK", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public int prepayOK(@RequestBody ReservationVO vo) {
		// 매개변수값으로 imp_uid, merchant_uid가 들어와야한다.
		vo.setSpaceId(1L);
		logger.info("VO:{}", vo);

		int flag = service.verifyPayInfo(vo);
		logger.info("result: {}", flag);

		return flag;
	}
	
//	// 보증금 결제, 결제완료 버튼 클릭 -> 현민님과 합칠 부분
	@RequestMapping(value = "/depositOK", method = RequestMethod.POST)
	@ResponseBody
	public int depositOK(@RequestBody ReservationVO vo) {
		// 매개변수값으로 imp_uid, merchant_uid가 들어와야한다.
		logger.info("VO:{}", vo);
		
//		int flag = service.verifyPayInfo(vo);
//		logger.info("result: {}", flag);
		
		int flag = service.depositOK(vo);
		logger.info("depositOK result: {}", flag);
//		
//		ReservationVO vo2 = service.reserve(vo);
//		logger.info("reserve result: {}", vo2);
		
		return 1;
	}

//	// 보증금 결제, 결제완료 버튼 클릭 -> 현민님과 합칠 부분
	@RequestMapping(value = "/reservePayOK", method = RequestMethod.POST)
	@ResponseBody
	public int reservePayOK(@RequestBody ReservationVO vo) {
		// 매개변수값으로 imp_uid, merchant_uid가 들어와야한다.
		vo.setSpaceId(1L);
		logger.info("VO:{}", vo);
		
		int flag = service.reserve(vo);
		logger.info("result: {}", flag);
		
		return flag;
	}
	
	// 결제취소 버튼 클릭 -> 현민님과 합칠 부분
	@RequestMapping(value = "/refund", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	public String refund(@RequestBody RefundVO vo) {
		// 매개변수값으로 imp_uid, merchant_uid가 들어와야한다.
		// vo.setSpaceId(1L);
		logger.info("VO:{}", vo);

		System.out.println(service.refund(vo));

		return "payment/pg";
	}

}
