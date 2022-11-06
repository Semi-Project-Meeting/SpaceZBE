package com.spaceZ.mypage;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;

import com.spaceZ.master.ManagerVO;
import com.spaceZ.reservation.ReservationVO;
import com.spaceZ.spaceZBE.ImageFAO;

@Repository
public class MypageDAOimpl implements MypageDAO {

	private static final Logger logger = LoggerFactory.getLogger(MypageDAOimpl.class);

	@Autowired
	SqlSession sqlSession;

	// 마이페이지 - 예약 현황
	@Override
	public List<ReservationVO> statusReserve(String memberId) {

		List<ReservationVO> vos = sqlSession.selectList("SQL_STATUS_RESERVE",memberId);

		return vos;
	}

	// 마이페이지 - 예약 이력
	@Override
	public List<ReservationVO> totalReserve(String memberId) {
		
		List<ReservationVO> vos = sqlSession.selectList("SQL_TOTAL_RESERVE",memberId);

		return vos;
	}

	@Override
	public List<ManagerVO> qnaSelectAll(String memberId) {
		// TODO Auto-generated method stub
		return null;
	}

}
