package com.spaceZ.member;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOimpl implements MemberDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberDAOimpl.class);

	@Autowired
	SqlSession sqlSession;
	
	@Autowired
	HttpSession session;

	@Override
	public int signUp(MemberVO vo) {
		int flag = 0;
		return flag;
	}

	@Override
	public int login(MemberVO vo) {
		int flag = 0;
		
		logger.info("login()...");

		MemberVO vo2 = sqlSession.selectOne("SQL_MEMBER_SELECT_ONE", vo);

		if(vo2 != null) {
			session.setAttribute("memberid", vo2.getMemberid());
			session.setAttribute("authority", vo2.getAuthority());
			flag = 1;
		}

		return flag;
	}

	@Override
	public int idCheck(MemberVO vo) {
		int flag = 0;
		return flag;
	}

	@Override
	public int sendEmail(MemberVO vo) {
		int flag = 0;
		return flag;
	}

}
