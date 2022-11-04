package com.spaceZ.mileage;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spaceZ.member.MemberVO;
import com.spaceZ.reservation.ReservationVO;
import com.spaceZ.serviceInfo.SpaceInfoVO;

@Repository
public class MileageDAOimpl implements MileageDAO {

	private static final Logger logger = LoggerFactory.getLogger(MileageDAOimpl.class);

	@Autowired
	SqlSession sqlSession;

	// 마일리지 적립
	@Override
	public int insertMileage(ReservationVO vo) {
		logger.info("insertMileage");
		logger.info("ReservationVO:{}", vo);
		int flag = 0;

		SpaceInfoVO spaceVO = sqlSession.selectOne("SQL_SELECT_ONE", vo.getSpaceId());
		logger.info("spaceVO:{}", spaceVO);

		MileageVO mileageVO = new MileageVO();
		mileageVO.setMemberId(vo.getMemberId());
		mileageVO.setSpaceName(spaceVO.getSpaceName());
		mileageVO.setScore((int) ((vo.getPrice()+vo.getMileage()) * 0.05));
		mileageVO.setSpaceId(vo.getSpaceId());
		mileageVO.setStatus("적립");
		logger.info("mileageVO:{}", mileageVO);

		flag = sqlSession.insert("SQL_MILEAGE_INSERT", mileageVO);

		return flag;
	}

	// 마일리지 사용
	@Override
	public int updateMileage(ReservationVO vo) {
		logger.info("updateMileage");
		logger.info("ReservationVO:{}", vo);
		
		int flag = 0;

		SpaceInfoVO spaceVO = sqlSession.selectOne("SQL_SELECT_ONE", vo.getSpaceId());
		logger.info("spaceVO:{}", spaceVO);

		MileageVO mileageVO = new MileageVO();
		mileageVO.setMemberId(vo.getMemberId());
		mileageVO.setSpaceName(spaceVO.getSpaceName());
		mileageVO.setScore(vo.getMileage() * -1);
		mileageVO.setSpaceId(vo.getSpaceId());
		mileageVO.setStatus("사용");
		logger.info("mileageVO:{}", mileageVO);

		flag = sqlSession.insert("SQL_MILEAGE_INSERT", mileageVO);

		return flag;
	}

	// 마일리지 취소
	@Override
	public int deleteMileage(ReservationVO vo) {
		logger.info("deleteMileage");
		logger.info("MileageVO:{}", vo);

		int flag = 0;

		SpaceInfoVO spaceVO = sqlSession.selectOne("SQL_SELECT_ONE", vo.getSpaceId());
		logger.info("spaceVO:{}", spaceVO);

		MileageVO mileageVO = new MileageVO();
		mileageVO.setMemberId(vo.getMemberId());
		mileageVO.setSpaceName(spaceVO.getSpaceName());
		mileageVO.setScore((int) (vo.getPrice() * 0.05) * -1);
		mileageVO.setSpaceId(vo.getSpaceId());
		mileageVO.setStatus("취소");
		logger.info("mileageVO:{}", mileageVO);

		flag = sqlSession.insert("SQL_MILEAGE_INSERT", mileageVO);

		return flag;
	}

	// 사용자 프로필 조회 - memberId 만 알면 됨.
	@Override
	public ProfileDTO selectAll(long memberid) {
		logger.info("selectAll");
		logger.info("memberid:{}", memberid);

		ProfileDTO dto = getTotal_score(memberid);
		
		MemberVO vo = sqlSession.selectOne("SQL_MEMBER_SELECT_ONE", memberid);
		dto.setEmail(vo.getEmail());
		dto.setImgname(vo.getImgname());
		dto.setMembername(vo.getMembername());
		
		return dto;
	}

	// 사용자 마일리지 조회 - memberId 만 알면 됨.
	@Override
	public ProfileDTO getTotal_score(long memberid) {
		logger.info("getTotal_score");
		logger.info("memberid:{}", memberid);
		
		ProfileDTO dto = new ProfileDTO();
		List<MileageVO> vos = sqlSession.selectList("SQL_MILEAGE_SELECTALL", memberid);
		logger.info("vos.size:{}", vos.size());
		dto.setVos(vos);
		int totalScore = 0;
		for (MileageVO mileageVO : vos) {
			totalScore += mileageVO.getScore();
		}
		dto.setTotal_score(totalScore);
		return dto;
	}

	// 사용한 마일리지 환급
	@Override
	public int refundMileage(ReservationVO vo) {
		logger.info("refundMileage");
		logger.info("vo:{}", vo);
		List<MileageVO> vos = sqlSession.selectList("SQL_MILEAGE_SPACE_SELECTALL", vo.getSpaceId());
		
		int flag = 0;
		
		if(vos.size()>0) {
			for (MileageVO mileageVO : vos) {
				if(mileageVO.getStatus().equals("사용")) {
					MileageVO vo2 = new MileageVO();
					vo2.setMemberId(vo.getMemberId());
					vo2.setSpaceName(mileageVO.getSpaceName());
					vo2.setScore(mileageVO.getScore()*-1);
					vo2.setSpaceId(vo.getSpaceId());
					vo2.setStatus("환급");
					logger.info("mileageVO:{}", vo2);

					flag = sqlSession.insert("SQL_MILEAGE_INSERT", vo2);
					logger.info("마일리지 환급 완료");
				}
			}
		}
		
		return flag;
	}

}
