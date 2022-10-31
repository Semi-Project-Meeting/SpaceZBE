package com.spaceZ.backoffice;


import java.util.ArrayList;
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

import com.spaceZ.reservation.ReservationVO;

@Controller
@RequestMapping("/back-office")
public class BackOfficeController {

	@Autowired
	BackOfficeService service;

	private static final Logger logger = LoggerFactory.getLogger(BackOfficeController.class);

	@ResponseBody 
	@RequestMapping(value = "/reserve", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	public String insert(@RequestBody ReservationVO vo) {
		
		logger.info("backoffice-reserve..");
		
		String txt = "{\"result\": OK}";
		
		List<ReservationVO> vos = service.duplicate(vo);
		if(vos.size()==0) {
			service.reserve(vo);
		} else if(vos.size() != 0) {
			txt = "{\"result\": 해당 시간 예약 취소를 먼저 진행해 주세요.}";
		}

		return txt;
	}
	
	@ResponseBody 
	@RequestMapping(value = "/office-cancel", method = RequestMethod.GET, produces = "application/json; charset=utf8")
	public String update(@RequestBody ReservationVO vo) {
		
		logger.info("office cancel..");
		
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
	
	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	@ResponseBody // 페이지가 아니라 값을 뿌려주는거다.
	public List<ReservationVO> selectAll(@RequestParam(value = "companyId") String companyId) {
		
		List<ReservationVO> vos = service.selectAll(companyId);
				
		return vos;
	}

}
