package com.spaceZ.search;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spaceZ.serviceInfo.SpaceInfoVO;

@Service
public class SearchService {
	@Autowired
	SearchDAO dao;

	public List<SpaceInfoVO> getCanResvSpaces(SearchVO vo) {
		return dao.getCanResvSpaces(vo);
	}

	public List<SpaceInfoVO> getAllSpaces() {
		return dao.getAllSpaces();
	}

}
