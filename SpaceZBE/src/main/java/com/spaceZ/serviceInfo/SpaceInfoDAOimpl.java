package com.spaceZ.serviceInfo;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.spaceZ.qna.QnaVO;
import com.spaceZ.review.ReviewVO;
import com.spaceZ.spaceZBE.ImageFAO;

@Repository
public class SpaceInfoDAOimpl implements SpaceInfoDAO {
	private static final Logger logger = LoggerFactory.getLogger(SpaceInfoDAOimpl.class);

	@Autowired
	SqlSession sqlSession;
	
	@Autowired
	ImageFAO fao;

	public SpaceInfoVO selectOne(long spaceId) {

		return sqlSession.selectOne("SQL_SELECT_ONE", spaceId);
	}

	@Override
	public double getRating(long spaceId) {
		if(sqlSession.selectOne("SQL_GET_RATING", spaceId)== null) {
			return -1;
		} else
			return sqlSession.selectOne("SQL_GET_RATING", spaceId);
	}

	@Override
	public List<ReviewVO> getReviews(long spaceId) {
		return sqlSession.selectList("SQL_GET_REVIEWS", spaceId);
	}

	@Override
	public List<QnaVO> getQnas(long spaceId) {
		return sqlSession.selectList("SQL_GET_QNAS", spaceId);
	}
	
	// spaceId 메서드 (spaceId 통일하기 위해서 생성)
	public SpaceInfoVO spaceId() {
		
		SpaceInfoVO vo = sqlSession.selectOne("SQL_SPACEID");
		logger.info("spaceId : {}..",vo);
		
		return vo;
	}

	// 사무공간 등록
	@Override
	public int insertSpace(SpaceInfoVO vo) {

		logger.info("사무공간 등록..");
		
		// spaceId 
		SpaceInfoVO spaceInfoVO = spaceId();
		vo.setSpaceId(spaceInfoVO.getSpaceId());
		
		// 다중 이미지 등록 
		List<MultipartFile> multipartFiles = vo.getMultipartFiles();
		ImagesVO imgvo = new ImagesVO();
		
		String imgName = "";
		int i = 0;
		for (MultipartFile data : multipartFiles) {
			imgName = fao.getImageName(data);
			if(i == 0) {
				vo.setImgName(imgName);
			}
			imgvo.setImgName(imgName);
			imgvo.setSpaceId(vo.getSpaceId());
			i++;
			sqlSession.insert("SQL_IMAGE_INSERT",imgvo);
		}
		
		int flag = sqlSession.insert("SQL_SPACE_INSERT", vo);
		
		logger.info("이미지저장 후 vo:{}", vo);
		
		return flag;
	}

	// 사무공간 수정
	@Override
	public int updateSpace(SpaceInfoVO vo) {
		logger.info("사무공간 수정..");

		int flag = sqlSession.insert("SQL_SPACE_UPDATE", vo);

		return flag;
	}
	
	// 사무공간 다중 이미지 출력.
	@Override
	public List<ImagesVO> getImages(long spaceId) {
		
		return sqlSession.selectList("SQL_GET_IMAGES", spaceId);
  }
  
	@Override
	public List<SpaceInfoVO> recentlyAdded() {
		return sqlSession.selectList("SQL_RECENTLY_ADDED");
	}

	@Override
	public List<SpaceInfoVO> recommendedSpace() {
		return sqlSession.selectList("SQL_RECOMMENDED_SPACE");
	}
}
