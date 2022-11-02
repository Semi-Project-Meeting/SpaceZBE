package com.spaceZ.serviceInfo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class SpaceInfoVO {

	private long spaceId;
	private long companyId;
	private String spaceName;
	private String info;
	private String moreInfo;
	private String type;
	private String location;
	private int price;
	private String phone_number;
	private List<MultipartFile> multipartFiles; 
	
	public SpaceInfoVO() {
	}
	
	public SpaceInfoVO(long space_id) {
		this.spaceId = space_id;
	}

	public SpaceInfoVO(long spaceId, long companyId, String spaceName, String info, String moreInfo,
			String type, String location, int price, String phone_number) {
		super();
		this.spaceId = spaceId;
		this.companyId = companyId;
		this.spaceName = spaceName;
		this.info = info;
		this.moreInfo = moreInfo;
		this.type = type;
		this.location = location;
		this.price = price;
		this.phone_number = phone_number;
	}

	public long getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(long spaceId) {
		this.spaceId = spaceId;
	}



	public long getCompanyId() {
		return companyId;
	}



	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}


	public String getSpaceName() {
		return spaceName;
	}



	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}



	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getMoreInfo() {
		return moreInfo;
	}

	public void setMoreInfo(String moreInfo) {
		this.moreInfo = moreInfo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public List<MultipartFile> getMultipartFiles() {
		return multipartFiles;
	}

	public void setMultipartFiles(List<MultipartFile> multipartFiles) {
		this.multipartFiles = multipartFiles;
	}

	@Override
	public String toString() {
		return "SpaceInfoVO [spaceId=" + spaceId + ", companyId=" + companyId + ", spaceName=" + spaceName + ", info="
				+ info + ", moreInfo=" + moreInfo + ", type=" + type + ", location=" + location + ", price=" + price
				+ ", phone_number=" + phone_number + ", multipartFiles=" + multipartFiles + "]";
	}

	
}