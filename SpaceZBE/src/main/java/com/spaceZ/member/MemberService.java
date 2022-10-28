package com.spaceZ.member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberService.class);

	@Autowired
	MemberDAO dao;

	public int signUp(MemberVO vo) {
		logger.info("signUp...");
		return dao.signUp(vo);
	}

	public int login(MemberVO vo) {
		logger.info("login...");
		return dao.login(vo);
	}

	public int idCheck(MemberVO vo) {
		logger.info("idCheck...");
		return dao.idCheck(vo);
	}

	public int sendEmail(MemberVO vo) {
		logger.info("sendEmail...");
		return dao.sendEmail(vo);
	}

}