package com.spaceZ.reservation;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationDAOimpl implements ReservationDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(ReservationDAOimpl.class);
	
	@Autowired
	SqlSession sqlSession;
	
	@Override
	// 예약하기
	public int reserve(ReservationVO vo) {
		int flag = sqlSession.insert("SQL_RESERVE",vo);
		
		return flag;
	}
	
	@Override
	// 오피스 예약 취소하기 (status 상태만 예약 취소로 변경한다.)
	public int officeCancel(ReservationVO vo) {
		
		int flag = sqlSession.delete("SQL_RESERVE_CANCEL",vo);
		
		return flag;
	}
	@Override
	// 데스크 회의실 예약 취소하기 (status 상태를 예약 취소로 바꾸고 결제만 환불 된다.)
	public int deskCancel(String reservationId) {
		logger.info("desk-Cancel..");
		
		// 예약 번호에 대한 예약한 시간 가져오기. 
		float reverse_time = sqlSession.selectOne("SQL_RESERVE_TIME",reservationId);
		
		logger.info("reverse_time : {}",reverse_time);
		
		// 예약한지 1시간 이내일 때 (전체 환불)
		if(reverse_time <= 1) {
			logger.info("{} : 예약한지 1시간이 지나지 않았습니다. (전체 환불 가능)",reverse_time);
			
		} 
		// 예약한지 1시간 이후일  (선결제만 환불 가능, 보증금 환불 불가능)
		else if(reverse_time > 1) {
			logger.info("{} : 예약한지 1시간이 지났으요 (보증금만 환불 가능)",reverse_time);
			
		}
		
		// 예약중 -> 예약취소
		int flag = sqlSession.delete("SQL_RESERVE_CANCEL",reservationId);
		
		return flag;
	}

	@Override
	// 예약 날짜 중복 확인 
	public List<ReservationVO> resvDuplicate(ReservationVO vo) {
		List<ReservationVO> vos = sqlSession.selectList("SQL_RESERVE_DUPLICATE",vo);
		
		logger.info("중복된 예약 개수 : {}",vos.size());
		
		return vos;
	}

}
