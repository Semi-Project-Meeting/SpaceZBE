package com.spaceZ.reservation;


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
	@RequestMapping(value = "/reserve", method = RequestMethod.POST)
	public String insertOK(@RequestBody ReservationVO vo) {
		
		logger.info("insert..{}", service.reserve(vo));
		
		String txt = "{\"result\": OK\"}";

		return txt;
	}

}
