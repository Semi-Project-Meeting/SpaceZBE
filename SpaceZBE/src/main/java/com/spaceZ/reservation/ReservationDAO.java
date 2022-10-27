package com.spaceZ.reservation;

import org.springframework.stereotype.Component;

@Component
public interface ReservationDAO {
	
	// 예약하기
	int reserve(ReservationVO vo);
	
	// 예약 취소하기
	int resvCancel(ReservationVO vo);
	
	// 중복 예약 체크하기
	int resvDuplicate();
	
}
