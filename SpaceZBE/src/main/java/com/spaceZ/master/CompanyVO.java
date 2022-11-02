package com.spaceZ.master;

public class CompanyVO {
	long companyId;
	long memberId;
	String company_name;
	
	public CompanyVO() {
		// TODO Auto-generated constructor stub
	}
	
	public CompanyVO(long companyId, long memberId, String company_name) {
		super();
		this.companyId = companyId;
		this.memberId = memberId;
		this.company_name = company_name;
	}

	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	public long getMemberId() {
		return memberId;
	}
	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	
	
}
