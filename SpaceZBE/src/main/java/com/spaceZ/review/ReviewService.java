package com.spaceZ.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
	@Autowired
	ReviewDAO dao;

	public int insert(ReviewVO vo) {
		return dao.insert(vo);
	}

	public int update(ReviewVO vo) {
		return dao.update(vo);
	}

	public int delete(ReviewVO vo) {
		return dao.delete(vo);
	}
	
}
