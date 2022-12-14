package com.spaceZ.serviceInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spaceZ.mileage.MileageService;
import com.spaceZ.payment.PaymentDAOimpl;
import com.spaceZ.payment.PaymentService;
import com.spaceZ.qna.QnaVO;
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
	
	@Autowired
	MileageService mileageService;
	
	@Autowired
	HttpSession session;
	
	private static final Logger logger = LoggerFactory.getLogger(SpaceInfoController.class);

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/spaceInfo", method = RequestMethod.GET)
	@ResponseBody
	public Map spaceInfo(Model model, long spaceId, long memberId) {

		logger.info("{}", spaceId);
		logger.info("{}", memberId);

		SpaceInfoVO vo = service.selectOne(spaceId);
		double avgRating = service.getRating(spaceId);
		List<QnaVO> qnas = service.getQnas(spaceId);
		List<ReviewVO> reviews = service.getReviews(spaceId);
		List<ImagesVO> images = service.getImages(spaceId);

		Map map = new HashMap();

		map.put("space", vo);
		
		if(avgRating != -1) {
			map.put("avgRating", avgRating);
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

		map.put("total_score", mileageService.getTotal_score(memberId).getTotal_score());
		
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/back-office/insert", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	public String insert(SpaceInfoVO vo) {

		logger.info("???????????? ?????? insert..{}");

		String txt = "{\"result\": OK}";

		int result = service.insertSpace(vo);

		if (result == 0) {
			txt = "{\"result\": ?????? ?????????????????????.}";
		}

		return txt;
	}

	@ResponseBody
	@RequestMapping(value = "/back-office/update", method = RequestMethod.GET, produces = "application/json; charset=utf8")
	public String update(@RequestBody SpaceInfoVO vo) {

		logger.info("???????????? ?????? update..{}");

		String txt = "{\"result\": OK}";

		int result = service.updateSpace(vo);

		if (result == 0) {
			txt = "{\"result\": ?????? ?????????????????????.}";
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
