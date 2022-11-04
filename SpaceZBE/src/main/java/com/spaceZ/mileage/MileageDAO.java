package com.spaceZ.mileage;

import com.spaceZ.reservation.ReservationVO;

public interface MileageDAO {

	public int insertMileage(ReservationVO vo); //mileage 적립
	public int updateMileage(ReservationVO vo); //mileage 사용
	public int deleteMileage(ReservationVO vo); //mileage 취소조치
	public ProfileDTO selectAll(long memberid); //mileage 조회-> profile 페이지 값들 반환
	public ProfileDTO getTotal_score(long memberid); //mileage 총점 반환
	public int refundMileage(ReservationVO vo);//마일리지 환급
}
