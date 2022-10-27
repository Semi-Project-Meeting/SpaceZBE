package com.spaceZ.reservation;

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int resvDuplicate() {
		// TODO Auto-generated method stub
		return 0;
	}

}
