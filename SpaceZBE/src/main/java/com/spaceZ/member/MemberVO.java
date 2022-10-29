package com.spaceZ.member;

import org.springframework.web.multipart.MultipartFile;

public class MemberVO {

	private long memberid;
	private String membername;
	private String email;
	private String password;
	private String authority;
	private String imgname;
	private MultipartFile multipartFile;

	public MemberVO() {
	}

	public MemberVO(String receiver) {
		this.email = receiver;
	}

	public long getMemberid() {
		return memberid;
	}

	public void setMemberid(long memberid) {
		this.memberid = memberid;
	}

	public String getMembername() {
		return membername;
	}

	public void setMembername(String membername) {
		this.membername = membername;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getImgname() {
		return imgname;
	}

	public void setImgname(String imgname) {
		this.imgname = imgname;
	}

	public MultipartFile getMultipartFile() {
		return multipartFile;
	}

	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authority == null) ? 0 : authority.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((imgname == null) ? 0 : imgname.hashCode());
		result = prime * result + (int) (memberid ^ (memberid >>> 32));
		result = prime * result + ((membername == null) ? 0 : membername.hashCode());
		result = prime * result + ((multipartFile == null) ? 0 : multipartFile.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberVO other = (MemberVO) obj;
		if (authority == null) {
			if (other.authority != null)
				return false;
		} else if (!authority.equals(other.authority))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (imgname == null) {
			if (other.imgname != null)
				return false;
		} else if (!imgname.equals(other.imgname))
			return false;
		if (memberid != other.memberid)
			return false;
		if (membername == null) {
			if (other.membername != null)
				return false;
		} else if (!membername.equals(other.membername))
			return false;
		if (multipartFile == null) {
			if (other.multipartFile != null)
				return false;
		} else if (!multipartFile.equals(other.multipartFile))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MemberVO [memberid=" + memberid + ", membername=" + membername + ", email=" + email + ", password="
				+ password + ", authority=" + authority + ", imgname=" + imgname + ", multipartFile=" + multipartFile
				+ "]";
	}

}
