package com.spaceZ.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReservationService {
	
	@Autowired
	ReservationDAO dao;
	
	// 예약하기
	public int reserve(ReservationVO vo) {
		
		return dao.reserve(vo);
	}
	
}
