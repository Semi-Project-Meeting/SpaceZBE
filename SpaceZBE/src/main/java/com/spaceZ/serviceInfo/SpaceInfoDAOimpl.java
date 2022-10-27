package com.spaceZ.serviceInfo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SpaceInfoDAOimpl implements SpaceInfoDAO {
	private static final Logger logger = LoggerFactory.getLogger(SpaceInfoDAOimpl.class);
	
	@Autowired
	SqlSession sqlSession;
	
	public SpaceInfoVO selectOne(int spaceId) {
		
		return sqlSession.selectOne("SQL_SELECT_ONE", spaceId);
	}

	@Override
	public double getRating(int spaceId) {
		return sqlSession.selectOne("SQL_GET_RATING", spaceId);
	}

	@Override
	public List<ReplyVO> getReplys(int spaceId) {
		return sqlSession.selectList("SQL_GET_REPLYS", spaceId);
	}

	@Override
	public List<ReviewVO> getReviews(int spaceId) {
		return sqlSession.selectList("SQL_GET_REVIEWS", spaceId);
	}

	@Override
	public List<QnaVO> getQnas(int spaceId) {
		return sqlSession.selectList("SQL_GET_QNAS", spaceId);
	}
}
