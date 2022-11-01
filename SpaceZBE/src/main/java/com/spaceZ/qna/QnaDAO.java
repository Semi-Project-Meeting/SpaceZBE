package com.spaceZ.qna;

import org.springframework.stereotype.Component;

@Component
public interface QnaDAO {

	int insertQna(QnaVO vo);

	int answerQna(QnaVO vo);

	int deleteQna(QnaVO vo);

	int deleteAnswer(QnaVO vo);

	
}
