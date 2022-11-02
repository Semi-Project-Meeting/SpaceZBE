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
	
	// 관리자 승인하기 (승인완료로 상태변경) 
	int approve(ManagerVO vo);
	
	// authority 관리자로 변경
	int promote(ManagerVO vo);
	
	// 관리자 거부 하기 (승인거부로 상태변경) 
	int disapprove(ManagerVO vo);
	
	// 업체 등록하기 
	int companyRegister(CompanyVO vo);
}
