package com.spaceZ.qna;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class QnaDAOimpl implements QnaDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int insertQna(QnaVO vo) {
		return sqlSession.insert("SQL_INSERT_QNA", vo);
	}

	@Override
	public int answerQna(QnaVO vo) {
		return sqlSession.update("SQL_ANSWER_QNA", vo);
	}

}
