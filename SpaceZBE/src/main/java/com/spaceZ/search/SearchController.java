package com.spaceZ.search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spaceZ.serviceInfo.SpaceInfoVO;

@Controller
public class SearchController {
	
	@Autowired
	SearchService service;
	
	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, List<SpaceInfoVO>> search(@RequestBody SearchVO vo) {
		vo.setSearchWord("%" + vo.getSearchWord() + "%");
		Map<String, List<SpaceInfoVO>> map = new HashMap<String, List<SpaceInfoVO>>();
		List<SpaceInfoVO> vos = service.getCanResvSpaces(vo);
		map.put("vos", vos);

		return map;
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, List<SpaceInfoVO>> searchGet(SearchVO vo) {
		vo.setSearchWord("%" + vo.getSearchWord() + "%");
		Map<String, List<SpaceInfoVO>> map = new HashMap<String, List<SpaceInfoVO>>();
		List<SpaceInfoVO> vos = service.getCanResvSpaces(vo);
		map.put("vos", vos);

		return map;
	}
	
//	@RequestMapping(value="/search", method = RequestMethod.GET)
//	public String search() {
//		logger.info("hello search");
//		
//		return "search";
//	}
	
	@RequestMapping(value="/test", method = RequestMethod.GET)
	public String test() {
		
		return "test";
	}
	
	@RequestMapping(value = "/searchAll", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, List<SpaceInfoVO>> searchAll() {
		Map<String, List<SpaceInfoVO>> map = new HashMap<String, List<SpaceInfoVO>>();
		List<SpaceInfoVO> vos = service.getAllSpaces();
		map.put("vos", vos);

		return map;
	}
}
