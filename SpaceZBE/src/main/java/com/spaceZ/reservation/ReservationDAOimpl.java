package com.spaceZ.reservation;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spaceZ.mileage.MileageService;
import com.spaceZ.mileage.MileageVO;
import com.spaceZ.payment.PaymentService;
import com.spaceZ.payment.RefundVO;

@Repository
public class ReservationDAOimpl implements ReservationDAO {

	private static final Logger logger = LoggerFactory.getLogger(ReservationDAOimpl.class);

	@Autowired
	SqlSession sqlSession;

	@Autowired
	PaymentService paymentService;

	@Autowired
	MileageService mileageService;

	@Override
	// 예약하기
	public int reserve(ReservationVO vo) {
		logger.info("reserve..");
		logger.info("vo:{}", vo);
		int flag = 0;
		// prepay: 선결제(000) or 보증금결제(001)로 desk,회의실 결제/ 후결제(002)로 오피스 결제예약
		if (vo.getPrepay().equals("000")) {
			flag = paymentService.verifyPayInfo(vo);
			vo.setPostpay_uid("000");
			// paystatus: 결제 전 001, 결제 완료 002, 결제 취소 000, 보증금 결제완료 003, 보증금 결제취소 004
			vo.setPayStatus("002");

			// 마일리지 사용 및 적립
			if (flag == 1) {
				// 마일리지 사용
				if (vo.getMileage() > 0) {
					mileageService.updateMileage(vo);
					logger.info("마일리지 사용 완료");
				}
				// 마일리지 적립
				mileageService.insertMileage(vo);
				logger.info("마일리지 적립 완료");
			}
		} else if (vo.getPrepay().equals("001")) {
			int origin_price = vo.getPrice();
			flag = paymentService.depositOK(vo);
			vo.setPayStatus("003");
			vo.setPrice(origin_price);
			// 마일리지 사용
			if (vo.getMileage() > 0) {
				mileageService.updateMileage(vo);
				logger.info("마일리지 사용 완료");
			}
		} else if (vo.getPrepay().equals("002")) {
			flag = paymentService.reserve(vo);
			vo.setPayStatus("001");
			// 마일리지 사용
			if (vo.getMileage() > 0) {
				mileageService.updateMileage(vo);
				logger.info("마일리지 사용 완료");
			}
		}

		logger.info("vo:{}", vo);
		// 결제가 제대로 이루어진다면, DB에 저장
		if (flag == 1) {
			flag = sqlSession.insert("SQL_RESERVE", vo);
			logger.info("예약 저장 완료");
		}

		return flag;
	}

	@Override
	// 오피스 예약 취소하기 (status 상태만 예약 취소로 변경한다.)
	public int officeCancel(ReservationVO vo) {
		logger.info("officeCancel..");
		int flag = 0;

		vo = sqlSession.selectOne("SQL_RESERVE_ONE", vo);

		if (vo.getStatus().equals("001")) {
			String postpay_uid = vo.getPostpay_uid();
			paymentService.refund(new RefundVO(postpay_uid, "후결제 예약취소", 0, vo.getMemberId()));
			flag = sqlSession.update("SQL_RESERVE_CANCEL", vo);
			// 사용한 마일리지 환급
			mileageService.refundMileage(vo);
			logger.info("마일리지 환급 완료");
		}
		return flag;
	}

	@Override
	// 데스크 회의실 예약 취소하기 (status 상태를 예약 취소로 바꾸고 결제만 환불 된다.)
	public int deskCancel(String reservationId) {
		logger.info("desk-Cancel..");
		int flag = 0;
		
		// 예약 번호에 대한 예약한 시간 가져오기.
		float reverse_time = sqlSession.selectOne("SQL_RESERVE_TIME", reservationId);

		logger.info("reverse_time : {}", reverse_time);

		// 결제 내용 확인.
		ReservationVO vo = sqlSession.selectOne("SQL_RESERVE_ONE", reservationId);
		if (vo.getStatus().equals("001")) {
			// prepay: 선결제(000) or 보증금결제(001)로 desk,회의실 결제
			if (vo.getPrepay().equals("000")) {
				String prepay_uid = vo.getPrepay_uid();
				paymentService.refund(new RefundVO(prepay_uid, "선결제 결제취소", vo.getPrice(), vo.getMemberId()));
				vo.setPayStatus("000");

				// 마일리지 취소
				mileageService.deleteMileage(vo);
				logger.info("마일리지 취소, 회수 완료");
				// 사용한 마일리지 환급
				mileageService.refundMileage(vo);

			} else {

				// 예약한지 1시간 이내일 때 (전체 환불)
				if (reverse_time <= 1) {
					logger.info("{} : 예약한지 1시간이 지나지 않았습니다. (전체 환불 가능)", reverse_time);
					String prepay_uid = vo.getPrepay_uid();
					paymentService.refund(
							new RefundVO(prepay_uid, "보증금결제 취소", (int) (vo.getPrice() * 0.2), vo.getMemberId()));
					String postpay_uid = vo.getPostpay_uid();
					paymentService.refund(new RefundVO(postpay_uid, "후결제 예약취소", 0, vo.getMemberId()));
					vo.setPayStatus("004");
					// 사용한 마일리지 환급
					mileageService.refundMileage(vo);
					logger.info("마일리지 환급 완료");
				}
				// 예약한지 1시간 이후일 (선결제만 환불 가능, 보증금 환불 불가능)
				else if (reverse_time > 1) {
					logger.info("{} : 예약한지 1시간이 지났으요 (선결제만 환불 가능)", reverse_time);
					String postpay_uid = vo.getPostpay_uid();
					paymentService.refund(new RefundVO(postpay_uid, "후결제 예약취소", 0, vo.getMemberId()));
					// 사용한 마일리지 환급
					mileageService.refundMileage(vo);
					logger.info("마일리지 환급 완료");
				}
			}
			// 예약중 -> 예약취소
			flag = sqlSession.update("SQL_RESERVE_CANCEL", vo);
		}
		return flag;
	}

	@Override
	// 예약 날짜 중복 확인
	public List<ReservationVO> resvDuplicate(ReservationVO vo) {
		List<ReservationVO> vos = sqlSession.selectList("SQL_RESERVE_DUPLICATE", vo);

		logger.info("중복된 예약 개수 : {}", vos.size());

		return vos;
	}

}
