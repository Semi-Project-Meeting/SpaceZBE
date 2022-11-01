package com.spaceZ.serviceInfo;

import java.util.List;

import org.springframework.stereotype.Component;

import com.spaceZ.qna.QnaVO;
import com.spaceZ.reply.ReplyVO;
import com.spaceZ.review.ReviewVO;

@Component
public interface SpaceInfoDAO {
	
	public SpaceInfoVO selectOne(long spaceId);

	public double getRating(long spaceId);

	public List<ReplyVO> getReplys(long spaceId);

	public List<ReviewVO> getReviews(long spaceId);

	public List<QnaVO> getQnas(long spaceId);
	
	// 사무공간 등록 (백오피스)
	public int insertSpace(SpaceInfoVO vo);
	
	// 사무공간 수정 (백오피스)
	public int updateSpace(SpaceInfoVO vo);

	public List<SpaceInfoVO> recentlyAdded();

	public List<SpaceInfoVO> recommendedSpace();
}
