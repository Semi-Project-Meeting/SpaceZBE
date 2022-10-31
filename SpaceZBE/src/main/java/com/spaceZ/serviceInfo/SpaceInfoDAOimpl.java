package com.spaceZ.serviceInfo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spaceZ.qna.QnaVO;
import com.spaceZ.reply.ReplyVO;
import com.spaceZ.review.ReviewVO;

@Repository
public class SpaceInfoDAOimpl implements SpaceInfoDAO {
	private static final Logger logger = LoggerFactory.getLogger(SpaceInfoDAOimpl.class);
	
	@Autowired
	SqlSession sqlSession;
	
	public SpaceInfoVO selectOne(long spaceId) {
		
		return sqlSession.selectOne("SQL_SELECT_ONE", spaceId);
	}

	@Override
	public double getRating(long spaceId) {
		if(sqlSession.selectOne("SQL_GET_RATING", spaceId)== null) {
			return -1;
		} else
			return sqlSession.selectOne("SQL_GET_RATING", spaceId);
	}

	@Override
	public List<ReplyVO> getReplys(long spaceId) {
		return sqlSession.selectList("SQL_GET_REPLYS", spaceId);
	}

	@Override
	public List<ReviewVO> getReviews(long spaceId) {
		return sqlSession.selectList("SQL_GET_REVIEWS", spaceId);
	}

	@Override
	public List<QnaVO> getQnas(long spaceId) {
		return sqlSession.selectList("SQL_GET_QNAS", spaceId);
	}
}
