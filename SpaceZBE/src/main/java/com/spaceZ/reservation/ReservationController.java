package com.spaceZ.reservation;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/reserve")
public class ReservationController {

	@Autowired
	ReservationService service;

	private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);

	@ResponseBody 
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	public String insert(@RequestBody ReservationVO vo) {
		
		logger.info("insert..{}");
		logger.info("vo:{}",vo);
		String txt = "{\"result\": OK}";
		
		List<ReservationVO> vos = service.duplicate(vo);
		if(vos.size()==0) {
			int result = service.reserve(vo);
			if(result == 0) {
				txt = "{\"result\": 결제 및 예약에 실패하였습니다.}";
			}
		} else if(vos.size() != 0) {
			txt = "{\"result\": 중복된 예약이 있습니다.}";
		}

		return txt;
	}
	
	@ResponseBody 
	@RequestMapping(value = "/office-cancel", method = RequestMethod.GET, produces = "application/json; charset=utf8")
	public String update(@RequestBody ReservationVO vo) {
		
		logger.info("office cancel..");
		logger.info("vo:{}",vo);
		String txt = "{\"result\": OK}";
		
		int result = service.officeCancel(vo);
		if(result == 0) {
			txt = "{\"result\": 예약을 취소할 수 없습니다.}";
		}
		
		return txt;
	}
	
	@ResponseBody 
	@RequestMapping(value = "/desk-cancel", method = RequestMethod.GET, produces = "application/json; charset=utf8")
	public String deskUpdate(@RequestParam(value = "reservationId") String reservationId) {
		
		logger.info("desk/meeting room cancel..");
		logger.info("reservationId : {}",reservationId);
		
		String txt = "{\"result\": OK}";
		
		int result = service.deskCancel(reservationId);
		
		if(result == 0) {
			txt = "{\"result\": 예약을 취소할 수 없습니다.}";
		}
		
		return txt;
	}

}
