package com.spaceZ.serviceInfo;

import java.util.List;

import org.springframework.stereotype.Component;

import com.spaceZ.qna.QnaVO;
import com.spaceZ.reply.ReplyVO;
import com.spaceZ.review.ReviewVO;

@Component
public interface SpaceInfoDAO {
	
	public SpaceInfoVO selectOne(int spaceId);

	public double getRating(int spaceId);

	public List<ReplyVO> getReplys(int spaceId);

	public List<ReviewVO> getReviews(int spaceId);

	public List<QnaVO> getQnas(int spaceId);
	
	// 사무공간 등록 (백오피스)
	public int insertSpace(SpaceInfoVO vo);
	
	// 사무공간 수정 (백오피스)
	public int updateSpace(SpaceInfoVO vo);
}
