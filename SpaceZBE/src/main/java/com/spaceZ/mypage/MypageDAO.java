package com.spaceZ.mypage;

import java.util.List;

import javax.mail.MessagingException;

import com.spaceZ.master.ManagerVO;
import com.spaceZ.reservation.ReservationVO;

public interface MypageDAO {
	

	// 마이페이지 - 예약 현황
	public List<ReservationVO> statusReserve(String memberId);
	
	// 마이페이지 - 예약 이력 
	public List<ReservationVO> totalReserve(String memberId);
	
	// 마이페이지 - 문의 내용 확인 
	public List<ManagerVO> qnaSelectAll(String memberId);;
	
}
