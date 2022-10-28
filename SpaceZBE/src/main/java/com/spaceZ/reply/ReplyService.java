package com.spaceZ.reply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyService {

	@Autowired
	ReplyDAO dao;
	
	public int insert(ReplyVO vo) {
		return dao.insert(vo);
	}

}
