package com.spaceZ.master;

import java.util.List;

import org.springframework.stereotype.Component;

import com.spaceZ.reservation.ReservationVO;

@Component
public interface ManagerDAO {
	
	// 업체 관리자로 신청하기 
	int apply(ManagerVO vo);
	
	// 신청 목록
	List<ManagerVO> selectAll();
	
}
