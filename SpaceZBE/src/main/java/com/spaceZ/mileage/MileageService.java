package com.spaceZ.mileage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spaceZ.reservation.ReservationVO;

@Service
public class MileageService {

	private static final Logger logger = LoggerFactory.getLogger(MileageService.class);
	
	@Autowired
	MileageDAO dao;
	
	public int insertMileage(ReservationVO vo) {
		logger.info("insertMileage");		
		return dao.insertMileage(vo);
	}

	public int updateMileage(ReservationVO vo) {
		logger.info("updateMileage");		
		return dao.updateMileage(vo);
	}

	public int deleteMileage(ReservationVO vo) {
		logger.info("deleteMileage");		
		return dao.deleteMileage(vo);
	}

	public ProfileDTO selectAll(long memberId) {
		logger.info("selectAll");		
		return dao.selectAll(memberId);
	}
	
	public ProfileDTO getTotal_score(long memberId) {
		logger.info("getTotal_score");		
		return dao.getTotal_score(memberId);
	}
	
	public int refundMileage(ReservationVO vo) {
		logger.info("refundMileage");		
		return dao.refundMileage(vo);
	}

}
