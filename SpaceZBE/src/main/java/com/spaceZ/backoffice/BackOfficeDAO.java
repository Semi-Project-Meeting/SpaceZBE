package com.spaceZ.backoffice;

import java.util.List;

import org.springframework.stereotype.Component;

import com.spaceZ.reservation.ReservationVO;

@Component
public interface BackOfficeDAO {
	
	// 백오피스 - 청소를 위한 예약 제외 하기
	int reserve(ReservationVO vo);
	
	// 백오피스 - 오피스 예약 취소하기
	int officeCancel(ReservationVO vo);
	
	// 백오피스 - 데스크 예약 취소하기
	int deskCancel(String ReservationId);
	
	// 백오피스 - 예약 제외 전 예약 확인 
	List<ReservationVO> resvDuplicate(ReservationVO vo);
	
	// 백오피스 - 예약현황
	List<ReservationVO> selectAll(String companyId);
	
}
