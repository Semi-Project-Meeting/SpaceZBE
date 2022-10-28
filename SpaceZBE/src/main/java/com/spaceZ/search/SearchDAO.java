package com.spaceZ.search;

import java.util.List;

import org.springframework.stereotype.Component;

import com.spaceZ.serviceInfo.SpaceInfoVO;

@Component
public interface SearchDAO {

	public List<SpaceInfoVO> getCanResvSpaces(SearchVO vo);

}
