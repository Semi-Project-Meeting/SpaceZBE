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
	@RequestMapping(value = "/spaceInfo.do", method = RequestMethod.GET)
	@ResponseBody
	public Map spaceInfo(Model model, int spaceId) {
		
		SpaceInfoVO vo = service.selectOne(spaceId);
		double avgRating = service.getRating(spaceId);
		List<QnaVO> qnas = service.getQnas(spaceId);
		List<ReplyVO> replys = service.getReplys(spaceId);
		List<ReviewVO> reviews = service.getReviews(spaceId);
		
		Map map = new HashMap();
		
		map.put("space", vo);
		map.put("avgRating", avgRating);
		map.put("replys", replys);
		map.put("reviews", reviews);
		map.put("qnas", qnas);
		return map;
	}
	
}
