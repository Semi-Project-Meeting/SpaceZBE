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
	
	// 마스터에게 관리자로 신청하기 
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
	

	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	@ResponseBody // 페이지가 아니라 값을 뿌려주는거다.
	public List<ManagerVO> selectAll() {
		
		List<ManagerVO> vos = service.selectAll();
				
		return vos;
	}

}
