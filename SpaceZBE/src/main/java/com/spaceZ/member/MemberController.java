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
 * 회원가입, 로그인, 이메일인증
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
		logger.info("vo : {}", vo);

		String txt = "{\"result\": OK}";

		int result = service.signUp(vo);

		if (result == 0) {
			txt = "{\"result\": 회원가입 실패.}";
		}

		return txt;
	}

	// 로그인
	@RequestMapping(value = "/member/login", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String login(@RequestBody MemberVO vo) {

		logger.info("/member/login..");
		logger.info("vo : {}", vo);

		String txt = "{\"result\": OK}";

		int result = service.login(vo);

		if (result == 0) {
			txt = "{\"result\": 로그인 실패.}";
		}

		return txt;
	}

	// 메일작성 전송
	@RequestMapping(value = "/member/mail", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String sendMail(@RequestBody EmailVO vo) {
		logger.info("Welcome sendMail..");
		logger.info("{}", vo);

		String txt = "{\"result\": 전송 실패.}";
		
		try {
			int num = service.sendEmail(vo);
			if(num != 0) {
				txt = "{\"result\": "+num+"}";
			} else if(num == 0) {
				txt = "{\"result\": 이미 존재하는 아이디입니다.}";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("failed...");
		}

		return txt;
	}

}
