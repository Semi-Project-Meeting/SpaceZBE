package com.spaceZ.serviceInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spaceZ.qna.QnaVO;
import com.spaceZ.reply.ReplyVO;
import com.spaceZ.review.ReviewVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class SpaceInfoController {
	
	@Autowired
	SpaceInfoService service;
	
	private static final Logger logger = LoggerFactory.getLogger(SpaceInfoController.class);
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/spaceInfo", method = RequestMethod.GET)
	@ResponseBody
	public Map spaceInfo(Model model, long spaceId) {
		logger.info("{}", spaceId);
		SpaceInfoVO vo = service.selectOne(spaceId);
		double avgRating = service.getRating(spaceId);
		List<QnaVO> qnas = service.getQnas(spaceId);
		List<ReplyVO> replys = service.getReplys(spaceId);
		List<ReviewVO> reviews = service.getReviews(spaceId);
		
		Map map = new HashMap();
		
		map.put("space", vo);
		if(avgRating != -1) {
			map.put("avgRating", avgRating);
		}
		if(replys != null) {
			map.put("replys", replys);			
		}
		if(reviews != null) {
			map.put("reviews", reviews);			
		}
		if(qnas != null) {
			map.put("qnas", qnas);
		}
		return map;
	}
	
}
