package com.spaceZ.reservation;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReservationController {

	@Autowired
	ReservationService service;

	private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);

	@ResponseBody 
	@RequestMapping(value = "/reserve", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	public String insertOK(@RequestBody ReservationVO vo) {
		
		logger.info("insert..{}");
		
		String txt = "{\"result\": OK}";
		
		List<ReservationVO> vos = service.duplicate(vo);
		if(vos.size()==0) {
			service.reserve(vo);
		} else if(vos.size() != 0) {
			txt = "{\"result\": 중복된 예약이 있습니다.}";
		}

		return txt;
	}

}
