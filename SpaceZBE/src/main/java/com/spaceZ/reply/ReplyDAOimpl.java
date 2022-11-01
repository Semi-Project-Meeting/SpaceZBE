package com.spaceZ.reply;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReplyDAOimpl implements ReplyDAO {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int insert(ReplyVO vo) {
		return sqlSession.insert("SQL_INSERT_REPLY", vo);
	}

	@Override
	public int update(ReplyVO vo) {
		return sqlSession.update("SQL_UPDATE_REPLY", vo);
	}

	@Override
	public int delete(ReplyVO vo) {
		return sqlSession.delete("SQL_DELETE_REPLY", vo);
	}

}
