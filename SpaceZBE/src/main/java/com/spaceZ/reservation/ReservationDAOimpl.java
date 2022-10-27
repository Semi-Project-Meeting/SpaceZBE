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
	public int reserve(ReservationVO vo) {
		int flag = sqlSession.insert("SQL_RESERVE",vo);
		
		return flag;
	}
	
	@Override
	public int resvCancel(ReservationVO vo) {
		return 0;
	}

	@Override
	public List<ReservationVO> resvDuplicate(ReservationVO vo) {
		List<ReservationVO> vos = sqlSession.selectList("SQL_RESERVE_DUPLICATE",vo);
		
		logger.info("중복된 예약 개수 : {}",vos.size());
		
		return vos;
	}

}
