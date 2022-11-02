package com.spaceZ.mileage;

import java.util.List;

public class ProfileDTO {

	private List<MileageVO> vos;

	private String membername;
	private String email;
	private String imgname;
	private int total_score;

	public ProfileDTO() {
		// TODO Auto-generated constructor stub
	}

	public ProfileDTO(List<MileageVO> vos, String membername, String email, String imgname, int total_score) {
		super();
		this.vos = vos;
		this.membername = membername;
		this.email = email;
		this.imgname = imgname;
		this.total_score = total_score;
	}

	public List<MileageVO> getVos() {
		return vos;
	}

	public void setVos(List<MileageVO> vos) {
		this.vos = vos;
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

	public String getImgname() {
		return imgname;
	}

	public void setImgname(String imgname) {
		this.imgname = imgname;
	}

	public int getTotal_score() {
		return total_score;
	}

	public void setTotal_score(int total_score) {
		this.total_score = total_score;
	}

	@Override
	public String toString() {
		return "ProfileDTO [vos=" + vos + ", membername=" + membername + ", email=" + email + ", imgname=" + imgname
				+ ", total_score=" + total_score + "]";
	}

}
