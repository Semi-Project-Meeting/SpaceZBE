package com.spaceZ.qna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class QnaController {
	@Autowired
	QnaService service;

	@RequestMapping(value = "/insertQna", method = RequestMethod.POST)
	@ResponseBody
	public String insertQna(@RequestBody QnaVO vo) {
		int flag = service.insertQna(vo);
		
		if(flag == 1) {
			return "{\"result\":\"OK\"}";
		} else {
			return "{\"result\":\"NOT OK\"}";
		}
	}
	
	@RequestMapping(value = "/deleteQna", method = RequestMethod.POST)
	@ResponseBody
	public String deleteQna(@RequestBody QnaVO vo) {
		int flag = service.deleteQna(vo);
		
		if(flag == 1) {
			return "{\"result\":\"OK\"}";
		} else {
			return "{\"result\":\"NOT OK\"}";
		}
	}
	
	@RequestMapping(value = "/answerQna", method = RequestMethod.POST)
	@ResponseBody
	public String answerQna(@RequestBody QnaVO vo) {
		int flag = service.answerQna(vo);
		
		if(flag == 1) {
			return "{\"result\":\"OK\"}";
		} else {
			return "{\"result\":\"NOT OK\"}";
		}
	}
	
	@RequestMapping(value = "/updateAnswer", method = RequestMethod.POST)
	@ResponseBody
	public String updateAnswer(@RequestBody QnaVO vo) {
		int flag = service.answerQna(vo);
		
		if(flag == 1) {
			return "{\"result\":\"OK\"}";
		} else {
			return "{\"result\":\"NOT OK\"}";
		}
	}
	
	@RequestMapping(value = "/deleteAnswer", method = RequestMethod.POST)
	@ResponseBody
	public String deleteAnswer(@RequestBody QnaVO vo) {
		int flag = service.deleteAnswer(vo);
		
		if(flag == 1) {
			return "{\"result\":\"OK\"}";
		} else {
			return "{\"result\":\"NOT OK\"}";
		}
	}
}
