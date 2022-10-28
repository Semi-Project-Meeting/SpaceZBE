package com.spaceZ.serviceInfo;

import java.util.List;

import org.springframework.stereotype.Component;

import com.spaceZ.review.ReviewVO;

@Component
public interface SpaceInfoDAO {
	
	public SpaceInfoVO selectOne(long spaceId);

	public double getRating(long spaceId);

	public List<ReplyVO> getReplys(long spaceId);

	public List<ReviewVO> getReviews(long spaceId);

	public List<QnaVO> getQnas(long spaceId);
}
