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
		mileageVO.setScore((int) (vo.getPrice() * 0.05));
		mileageVO.setStatus("적립");
		logger.info("mileageVO:{}", mileageVO);

		flag = sqlSession.insert("SQL_MILEAGE_INSERT", mileageVO);

		return flag;
	}

	// 마일리지 사용
	@Override
	public int updateMileage(MileageVO vo) {
		logger.info("deleteMileage");
		logger.info("MileageVO:{}", vo);
		
		int usedMileage = 5;
		long spaceId = 1L;
		int flag = 0;

		SpaceInfoVO spaceVO = sqlSession.selectOne("SQL_SELECT_ONE", spaceId);
		logger.info("spaceVO:{}", spaceVO);

		MileageVO mileageVO = new MileageVO();
		mileageVO.setMemberId(vo.getMemberId());
		mileageVO.setSpaceName(spaceVO.getSpaceName());
		mileageVO.setScore(usedMileage * -1);
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
		mileageVO.setStatus("취소");
		logger.info("mileageVO:{}", mileageVO);

		flag = sqlSession.insert("SQL_MILEAGE_INSERT", mileageVO);

		return flag;
	}

	// 사용자 마일리지 조회 - memberId 만 알면 됨.
	@Override
	public ProfileDTO selectAll(long memberid) {
		logger.info("selectAll");
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
		
		MemberVO vo = sqlSession.selectOne("SQL_MEMBER_SELECT_ONE", memberid);
		dto.setEmail(vo.getEmail());
		dto.setImgname(vo.getImgname());
		dto.setMembername(vo.getMembername());
		
		return dto;
	}

}
