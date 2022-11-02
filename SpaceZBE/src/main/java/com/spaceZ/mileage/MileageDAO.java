package com.spaceZ.mileage;

import java.util.List;

import com.spaceZ.reservation.ReservationVO;

public interface MileageDAO {

	public int insertMileage(ReservationVO vo); //mileage 적립
	public int updateMileage(MileageVO vo); //mileage 사용
	public int deleteMileage(ReservationVO vo); //mileage 취소조치
	public ProfileDTO selectAll(long memberId); //mileage 조회-> profile 페이지 값들 반환
	
}
