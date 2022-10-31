package com.spaceZ.master;

public class ManagerVO {
	long managerId;
	long memberId;
	String company_name;
	String status; // 관리자로 승인 했는지 상태 (승인 대기:1 , 승인완료:2 , 승인취소:0)
	
	public ManagerVO() {
		
	}
	
	public ManagerVO(long managerId, long memberId, String company_name,String status) {
		super();
		this.managerId = managerId;
		this.memberId = memberId;
		this.company_name = company_name;
		this.status = status;
	}

	public long getManagerId() {
		return managerId;
	}
	public void setManagerId(long managerId) {
		this.managerId = managerId;
	}
	public long getMemberId() {
		return memberId;
	}
	public void setMemberId(long memeberId) {
		this.memberId = memeberId;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
