package com.spaceZ.qna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QnaService {
	@Autowired
	QnaDAO dao;

	public int insertQna(QnaVO vo) {
		return dao.insertQna(vo);
	}

	public int answerQna(QnaVO vo) {
		return dao.answerQna(vo);
	}

	public int deleteQna(QnaVO vo) {
		return dao.deleteQna(vo);
	}

	public int deleteAnswer(QnaVO vo) {
		return dao.deleteAnswer(vo);
	}

}
