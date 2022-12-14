package com.spaceZ.master;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spaceZ.reservation.ReservationVO;

@Repository
public class ManagerDAOimpl implements ManagerDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(ManagerDAOimpl.class);
	
	@Autowired
	SqlSession sqlSession;
	
	@Override
	// 관리자 신청하기
	public int apply(ManagerVO vo) {
		int flag = sqlSession.insert("SQL_MANAGER_APPLY",vo);
		
		return flag;
	}
	
	// 신청 목록
	@Override
	public List<ManagerVO> selectAll() {
		List<ManagerVO> vos = sqlSession.selectList("SQL_MANAGER_SELECT_ALL");
		
		return vos;
	}
	
	// 관리자로 승인하기 (승인완료 상태로 바꾸기)
	@Override
	public int approve(ManagerVO vo) {
		
		// 먼저 업체 등록하기 
		int company = companyRegister(vo.memberId);
		if(company == 0) {
			logger.info("업체 등록 안됨.");
		}
		int flag = sqlSession.update("SQL_APPROVE_STATUS",vo);
		
		return flag;
	}
	
	// Authority manager로 바꾸기.  
	@Override
	public int promote(ManagerVO vo) {
		int flag = sqlSession.update("SQL_PROMOTE_AUTHORITY",vo);
		
		return flag;
	}

	// 관리자신청 거부 하기 (승인거부 상태로 바꾸기)
	@Override
	public int disapprove(ManagerVO vo) {
		
		int flag = sqlSession.update("SQL_DISAPPROVE_STATUS",vo);
		
		return flag;
	}
	
	// 업체 등록하기
	public int companyRegister(long memberId) {
		
		int flag = sqlSession.insert("SQL_COMPANY_REGISTER",memberId);
		
		return flag;
	}
	
}
