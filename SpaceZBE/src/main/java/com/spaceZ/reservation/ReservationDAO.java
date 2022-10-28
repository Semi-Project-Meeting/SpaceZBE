package com.spaceZ.reservation;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public interface ReservationDAO {
	
	// 예약하기
	int reserve(ReservationVO vo);
	
	// 오피스 예약 취소하기
	int officeCancel(ReservationVO vo);
	
	// 데스크 예약 취소하기
	int deskCancel(String ReservationId);
	
	// 중복 예약 체크하기
	List<ReservationVO> resvDuplicate(ReservationVO vo);
	
}
