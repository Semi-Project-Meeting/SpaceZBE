package com.spaceZ.mypage;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spaceZ.master.ManagerVO;
import com.spaceZ.reservation.ReservationVO;

/**
 * 회원가입, 회원수정, (회원정보조회), 로그인, 로그아웃, 이메일인증(아이디 중복확인)
 */
@Controller
@RequestMapping("/mypage")
public class MypageController {

	private static final Logger logger = LoggerFactory.getLogger(MypageController.class);

	@Autowired
	MypageService service;

	// 마이페이지 - 예약현황
	@RequestMapping(value = "/status-reserve", method = RequestMethod.GET)
	@ResponseBody
	public List<ReservationVO> statusSelectAll(@RequestParam(value = "memberId") String memberId) {
		
		List<ReservationVO> vos = service.statusSelectAll(memberId);
				
		return vos;
	}
	
	// 마이페이지 - 예약이력 
	@RequestMapping(value = "/total-reserve", method = RequestMethod.GET)
	@ResponseBody
	public List<ReservationVO> totalSelectAll(@RequestParam(value = "memberId") String memberId) {
		
		List<ReservationVO> vos = service.totalSelectAll(memberId);
		
		return vos;
	}
	
	// 문의 내용 확인
	@RequestMapping(value = "/qna-selectAll", method = RequestMethod.GET)
	@ResponseBody 
	public List<ManagerVO> qnaSelectAll(@RequestParam(value = "memberId") String memberId) {
		
		List<ManagerVO> vos = service.qnaSelectAll(memberId);
				
		return vos;
	}
}
