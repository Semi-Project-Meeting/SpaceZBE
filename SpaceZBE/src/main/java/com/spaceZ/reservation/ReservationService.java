package com.spaceZ.reservation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

	@Autowired
	ReservationDAO dao;

	// 예약하기
	public int reserve(ReservationVO vo) {
		int result = 0;

		result = dao.reserve(vo);

		return result;
	}

	// 예약 중복체크
	public List<ReservationVO> duplicate(ReservationVO vo) {

		List<ReservationVO> vos = dao.resvDuplicate(vo);

		return vos;
	}

}
