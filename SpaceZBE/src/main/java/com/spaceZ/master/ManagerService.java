package com.spaceZ.master;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spaceZ.reservation.ReservationVO;


@Service
public class ManagerService {

	@Autowired
	ManagerDAO dao;

	// 백오피스 예약제외 
	public int apply(ManagerVO vo) {
		int result = 0;

		result = dao.apply(vo);

		return result;
	}
	
	// 백오피스 - 업체 관리자 예약 현황
	public List<ManagerVO> selectAll() {
		
		return dao.selectAll();
	}


}
