package com.spaceZ.member;

public interface MemberDAO {
	
	public int signUp(MemberVO vo);
	public int login(MemberVO vo);
	public int idCheck(MemberVO vo);
	public int sendEmail(MemberVO vo);
	
}
