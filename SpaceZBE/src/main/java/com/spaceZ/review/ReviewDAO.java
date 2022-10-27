package com.spaceZ.review;

import org.springframework.stereotype.Component;

@Component
public interface ReviewDAO {

	public int insert(ReviewVO vo);

}
