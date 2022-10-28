package com.spaceZ.member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 결제시스텀
 */
@Controller
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	MemberService service;

	// 회원가입
	@RequestMapping(value = "/member/signUp", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String signUp(@RequestBody MemberVO vo) {

		logger.info("/member/signUp..");
		logger.info("vo : {}",vo);
		
		String txt = "{\"result\": OK}";
		
		int result = service.signUp(vo);
		
		if(result == 0) {
			txt = "{\"result\": 회원가입 실패.}";
		}
		
		return txt;
	}
	
	// 로그인
	@RequestMapping(value = "/member/login", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String login(@RequestBody MemberVO vo) {
		
		logger.info("/member/login..");
		logger.info("vo : {}",vo);
		
		String txt = "{\"result\": OK}";
		
		int result = service.login(vo);
		
		if(result == 0) {
			txt = "{\"result\": 로그인 실패.}";
		}
		
		return txt;
	}



}
