package com.spaceZ.member;

import java.util.concurrent.ThreadLocalRandom;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;

import com.spaceZ.spaceZBE.ImageFAO;

@Repository
public class MemberDAOimpl implements MemberDAO {

	private static final Logger logger = LoggerFactory.getLogger(MemberDAOimpl.class);

	@Autowired
	SqlSession sqlSession;

	@Autowired
	HttpSession session;

	@Autowired
	JavaMailSender javaMailSender;
	
	
	@Autowired
	ImageFAO fao;

	@Override
	public int signUp(MemberVO vo) {
		logger.info("signUp()...");
		logger.info("{}", vo);
		
		int flag = 0;

		MemberVO vo2 = sqlSession.selectOne("SQL_MEMBER_EMAIL_CHECK", vo);
		if(vo2==null) {
			flag = sqlSession.insert("SQL_MEMBER_INSERT", vo);
		}
		session.removeAttribute("certificationNum");

		return flag;
	}
	
	@Override
	public int update(MemberVO vo) {
		logger.info("update()...");
		logger.info("{}", vo);
		
		//image 이름 추출 및 파일 업로드
		String imgname = fao.getImageName(vo.getMultipartFile());
		vo.setImgname(imgname);
		logger.info("이미지저장 후 vo:{}", vo);
		
		int flag = sqlSession.update("SQL_MEMBER_UPDATE", vo);

		return flag;
	}

	@Override
	public int login(MemberVO vo) {
		int flag = 0;

		logger.info("login()...");

		MemberVO vo2 = sqlSession.selectOne("SQL_MEMBER_LOGIN", vo);

		if (vo2 != null) {
			session.setAttribute("memberid", vo2.getMemberid());
			if(vo2.getAuthority().equals("manager")) {
				long companyId = sqlSession.selectOne("SQL_COMPANY_SELECT_ONE", vo2);
				logger.info("companyid:{}",companyId);
			session.setAttribute("companyId", companyId);
			}
			session.setAttribute("authority", vo2.getAuthority());
			flag = 1;
		}

		return flag;
	}

	@Override
	public int idCheck(MemberVO vo) {
		int flag = 1;

		logger.info("idCheck()...");
		logger.info("vo:{}",vo);

		MemberVO vo2 = sqlSession.selectOne("SQL_MEMBER_EMAIL_CHECK", vo);
		logger.info("vo2:{}", vo2);
		
		if (vo2 != null) {
			flag = 0;
		}

		return flag;
	}

	@Override
	public int sendEmail(EmailVO vo) throws MessagingException {
		logger.info("sendEmail");
		// 아이디 중복 확인
		int flag = idCheck(new MemberVO(vo.getReceiver()));
		if (flag == 0) {
			return flag;
		}
		// 이메일 인증번호 -> 난수생성
		int num = ThreadLocalRandom.current().nextInt(100000, 1000000);

		vo.setSubject("[SpaceZ] 인증번호를 입력해주세요.");
		vo.setContent("인증번호 [" + num + "]를 인증 칸에 입력하세요.");
		session.setAttribute("certificationNum", num);
		logger.info("{}", vo);

		// createMimeMessage 로 초기화
		MimeMessage msg = javaMailSender.createMimeMessage();
		// 내용을 setting

		msg.setSubject(vo.getSubject());
		msg.setText(vo.getContent());
		msg.setRecipient(RecipientType.TO, new InternetAddress(vo.getReceiver()));

		javaMailSender.send(msg);

		return num;
	}

}
