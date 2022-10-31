package com.spaceZ.serviceInfo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spaceZ.qna.QnaVO;
import com.spaceZ.reply.ReplyVO;
import com.spaceZ.review.ReviewVO;

@Service
public class SpaceInfoService {
	private static final Logger logger = LoggerFactory.getLogger(SpaceInfoService.class);
	
	@Autowired
	SpaceInfoDAO dao;
	
	public SpaceInfoVO selectOne(int spaceId) {
		SpaceInfoVO vo = dao.selectOne(spaceId);
		return vo;
	}

	public double getRating(int spaceId) {
		return dao.getRating(spaceId);
	}

	public List<ReplyVO> getReplys(int spaceId) {
		return dao.getReplys(spaceId);
	}

	public List<ReviewVO> getReviews(int spaceId) {
		return dao.getReviews(spaceId);
	}

	public List<QnaVO> getQnas(int spaceId) {
		return dao.getQnas(spaceId);
	}
	
	// 사무 공간 등록 
	public int insertSpace(SpaceInfoVO vo) {
		
		return dao.insertSpace(vo);
	}
	
	// 사무 공간 수정
	public int updateSpace(SpaceInfoVO vo) {
		
		return dao.updateSpace(vo);
	}
}
