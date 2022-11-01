package com.spaceZ.master;


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
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	ManagerService service;

	private static final Logger logger = LoggerFactory.getLogger(ManagerController.class);
	
	// 마스터에게 매니저로 신청하기 
	@ResponseBody 
	@RequestMapping(value = "/apply", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	public String insert(@RequestBody ManagerVO vo) {
		
		logger.info("manager apply..");
		
		String txt = "{\"result\": OK}";
		int result = service.apply(vo);
		
		if(result==0) {
			txt = "{\"result\": 신청이 불가 합니다.}";
		}

		return txt;
	}
	
	// 매니저 신청 목록 보기
	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	@ResponseBody 
	public List<ManagerVO> selectAll() {
		
		List<ManagerVO> vos = service.selectAll();
				
		return vos;
	}
	
	// 매니저 신청 승인하기. 
	@RequestMapping(value = "/approve", method = RequestMethod.GET, produces = "application/json; charset=utf8")
	@ResponseBody 
	public String approve(@RequestBody ManagerVO vo) {
		
		// 승인완료로 상태 변경
		int result = service.approve(vo);
		// authority 매니저로 승진 
		int result2 = service.promote(vo);
		String txt = "{\"result\": OK}";
		
		if(result == 0 || result2 == 0) {
			txt = "{\"result\": 승인 실패}";
		}
		
		return txt;
	}
	
	// 매니저 신청 거부하기. 
	@RequestMapping(value = "/disapprove", method = RequestMethod.GET, produces = "application/json; charset=utf8")
	@ResponseBody 
	public String disapprove(@RequestBody ManagerVO vo) {
		
		// 승인완료로 상태 변경
		int result = service.disapprove(vo);
		// authority 매니저로 승진 
		String txt = "{\"result\": OK}";
		
		if(result == 0) {
			txt = "{\"result\": 거부 실패}";
		}
		
		return txt;
	}

}
