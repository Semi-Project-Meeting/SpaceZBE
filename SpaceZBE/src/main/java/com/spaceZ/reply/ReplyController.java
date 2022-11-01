package com.spaceZ.reply;

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
public class ReplyController {
	
	@Autowired
	ReplyService service;
	
	private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);
	
	@RequestMapping(value = "/insertReply", method = RequestMethod.POST)
	@ResponseBody
	public String insertReply(@RequestBody ReplyVO vo) {
		
		int result = service.insert(vo);
		
		if(result == 0) {
			return "{\"result\":\"NOT OK\"}";
		} else {
			return "{\"result\":\"OK\"}";
		}
	}
	
	@RequestMapping(value = "/updateReply", method = RequestMethod.POST)
	@ResponseBody
	public String updateReply(@RequestBody ReplyVO vo) {
		
		int result = service.update(vo);
		
		if(result == 0) {
			return "{\"result\":\"NOT OK\"}";
		} else {
			return "{\"result\":\"OK\"}";
		}
	}
	
	@RequestMapping(value = "/deleteReply", method = RequestMethod.POST)
	@ResponseBody
	public String deleteReply(@RequestBody ReplyVO vo) {
		
		int result = service.delete(vo);
		
		if(result == 0) {
			return "{\"result\":\"NOT OK\"}";
		} else {
			return "{\"result\":\"OK\"}";
		}
	}
	
}
