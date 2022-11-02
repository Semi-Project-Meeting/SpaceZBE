package com.spaceZ.mypage;

import java.util.List;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spaceZ.member.MemberDAO;
import com.spaceZ.reservation.ReservationVO;

@Service
public class MypageService {
	
	private static final Logger logger = LoggerFactory.getLogger(MypageService.class);
	
	@Autowired
	MypageDAO dao;
	
	// 마이페이지 - 예약 현황
	public List<ReservationVO> statusSelectAll(String memberId) {
		
		return dao.statusReserve(memberId);
	}
	
	// 마이페이지 - 예약 이력 
	public List<ReservationVO> totalSelectAll(String memberId) {
		
		return dao.totalReserve(memberId);
	}

	

}
