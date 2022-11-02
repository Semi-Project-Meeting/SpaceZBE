package com.spaceZ.search;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spaceZ.serviceInfo.SpaceInfoVO;

@Repository
public class SearchDAOimpl implements SearchDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<SpaceInfoVO> getCanResvSpaces(SearchVO vo) {
		return sqlSession.selectList("SQL_SELECT_CAN_RESV_SPACES", vo);
	}

	@Override
	public List<SpaceInfoVO> getAllSpaces() {
		return sqlSession.selectList("SQL_SELECT_ALL_SPACES");
	}

}
