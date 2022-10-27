package com.spaceZ.serviceInfo;

import java.util.List;

public interface SpaceInfoDAO {
	
	public SpaceInfoVO selectOne(int spaceId);

	public double getRating(int spaceId);

	public List<ReplyVO> getReplys(int spaceId);

	public List<ReviewVO> getReviews(int spaceId);

	public List<QnaVO> getQnas(int spaceId);
}
