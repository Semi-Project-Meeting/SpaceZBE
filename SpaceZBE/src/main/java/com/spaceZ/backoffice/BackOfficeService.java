package com.spaceZ.backoffice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spaceZ.reservation.ReservationVO;


@Service
public class BackOfficeService {

	@Autowired
	BackOfficeDAO dao;

	// 백오피스 예약제외 
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
	
	// 백오피스 - 오피스 예약 취소하기
	public int officeCancel(ReservationVO vo) {
		
		return dao.officeCancel(vo);
	}
	
	// 백오피스 - 데스크, 회의실 예약 취소하기
	public int deskCancel(String reservationId) {
		
		return dao.deskCancel(reservationId);
	}
	
	// 백오피스 - 업체 관리자 예약 현황
	public List<ReservationVO> selectAll(String companyId) {
		
		return dao.selectAll(companyId);
	}

}
