package com.spaceZ.serviceInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spaceZ.payment.PaymentDAOimpl;
import com.spaceZ.payment.PaymentService;
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
	
	@Autowired
	PaymentDAOimpl dao;
	
	@Autowired
	PaymentService paymentService;
	
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
		List<ImagesVO> images = service.getImages(spaceId);

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
		if(images != null) {
			map.put("images", images);
		}
		
		map.put("merchant_uid", paymentService.getMerchant_uid());
		
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/back-office/insert", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	public String insert(SpaceInfoVO vo) {

		logger.info("사무공간 등록 insert..{}");

		String txt = "{\"result\": OK}";

		int result = service.insertSpace(vo);

		if (result == 0) {
			txt = "{\"result\": 등록 실패하였습니다.}";
		}

		return txt;
	}

	@ResponseBody
	@RequestMapping(value = "/back-office/update", method = RequestMethod.GET, produces = "application/json; charset=utf8")
	public String update(@RequestBody SpaceInfoVO vo) {

		logger.info("사무공간 수정 update..{}");

		String txt = "{\"result\": OK}";

		int result = service.updateSpace(vo);

		if (result == 0) {
			txt = "{\"result\": 수정 실패하였습니다.}";
		}

		return txt;
	}

	@ResponseBody
	@RequestMapping(value = "/recentlyAdded", method = RequestMethod.GET)
	public Map<String, List<SpaceInfoVO>> update() {
		List<SpaceInfoVO> vos = service.recentlyAdded();

		Map<String, List<SpaceInfoVO>> map = new HashMap<String, List<SpaceInfoVO>>();
		
		map.put("vos", vos);

		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/recommendedSpace", method = RequestMethod.GET)
	public Map<String, List<SpaceInfoVO>> recommendedSpace() {
		List<SpaceInfoVO> vos = service.recommendedSpace();

		Map<String, List<SpaceInfoVO>> map = new HashMap<String, List<SpaceInfoVO>>();
		
		map.put("vos", vos);

		return map;
	}
}
