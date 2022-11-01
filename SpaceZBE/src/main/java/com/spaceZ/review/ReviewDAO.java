package com.spaceZ.review;

import org.springframework.stereotype.Component;

@Component
public interface ReviewDAO {

	public int insert(ReviewVO vo);

	public int update(ReviewVO vo);

	public int delete(ReviewVO vo);

}
