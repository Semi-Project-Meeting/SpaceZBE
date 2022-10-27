package com.spaceZ.reply;

import org.springframework.stereotype.Component;

@Component
public interface ReplyDAO {

	int insert(ReplyVO vo);
	
}
