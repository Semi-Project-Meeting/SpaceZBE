package com.spaceZ.member;

import java.util.List;

import javax.mail.MessagingException;

import com.spaceZ.reservation.ReservationVO;

public interface MemberDAO {
	
	public int signUp(MemberVO vo);
	public long login(MemberVO vo);
	public int idCheck(MemberVO vo);
	public int sendEmail(EmailVO vo) throws MessagingException;
	public int update(MemberVO vo);
	long companyId(MemberVO vo);
	String authority(MemberVO vo);
	
}
