package com.spaceZ.review;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spaceZ.review.ReviewVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ReviewController {
	
	@Autowired
	ReviewService service;
	
	private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);
	
	@RequestMapping(value = "/insertReview", method = RequestMethod.POST)
	@ResponseBody
	public String insertReview(@RequestBody ReviewVO vo) {
		
		int result = service.insert(vo);
		
		if(result == 0) {
			return "{\"result\":\"NOT OK\"}";
		} else {
			return "{\"result\":\"OK\"}";
		}
	}
	
	@RequestMapping(value = "/updateReview", method = RequestMethod.POST)
	@ResponseBody
	public String updateReview(@RequestBody ReviewVO vo) {
		
		int result = service.update(vo);
		
		if(result == 0) {
			return "{\"result\":\"NOT OK\"}";
		} else {
			return "{\"result\":\"OK\"}";
		}
	}
	
	@RequestMapping(value = "/deleteReview", method = RequestMethod.POST)
	@ResponseBody
	public String deleteReview(@RequestBody ReviewVO vo) {
		
		int result = service.delete(vo);
		
		if(result == 0) {
			return "{\"result\":\"NOT OK\"}";
		} else {
			return "{\"result\":\"OK\"}";
		}
	}
}
