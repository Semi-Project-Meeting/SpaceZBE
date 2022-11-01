package com.spaceZ.review;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewDAOimpl implements ReviewDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int insert(ReviewVO vo) {
		return sqlSession.insert("SQL_INSERT_REVIEW", vo);
	}

	@Override
	public int update(ReviewVO vo) {
		return sqlSession.update("SQL_UPDATE_REVIEW", vo);
	}

	@Override
	public int delete(ReviewVO vo) {
		return sqlSession.delete("SQL_DELETE_REVIEW", vo);
	}

}
