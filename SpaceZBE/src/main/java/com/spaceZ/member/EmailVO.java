package com.spaceZ.member;

public class EmailVO {

	private String receiver;
	private String subject;
	private String content;
	private String regdate;
	
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	@Override
	public String toString() {
		return "EmailVO [receiver=" + receiver + ", subject=" + subject + ", content=" + content + ", regdate="
				+ regdate + "]";
	}
	
	
	
	
}
