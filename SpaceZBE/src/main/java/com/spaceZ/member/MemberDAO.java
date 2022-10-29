package com.spaceZ.member;

import javax.mail.MessagingException;

public interface MemberDAO {
	
	public int signUp(MemberVO vo);
	public int login(MemberVO vo);
	public int idCheck(MemberVO vo);
	public int sendEmail(EmailVO vo) throws MessagingException;
	public int update(MemberVO vo);
	
}
