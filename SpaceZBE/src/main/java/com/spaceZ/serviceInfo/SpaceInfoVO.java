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
	private String imgName;
	private List<MultipartFile> multipartFiles; 
	
	public SpaceInfoVO() {
	}
	
	public SpaceInfoVO(long space_id) {
		this.spaceId = space_id;
	}

	public SpaceInfoVO(long spaceId, long companyId, String spaceName, String info, String moreInfo, String type,
			String location, int price, String phone_number, String imgName, List<MultipartFile> multipartFiles) {
		this.spaceId = spaceId;
		this.companyId = companyId;
		this.spaceName = spaceName;
		this.info = info;
		this.moreInfo = moreInfo;
		this.type = type;
		this.location = location;
		this.price = price;
		this.phone_number = phone_number;
		this.imgName = imgName;
		this.multipartFiles = multipartFiles;
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

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public List<MultipartFile> getMultipartFiles() {
		return multipartFiles;
	}

	public void setMultipartFiles(List<MultipartFile> multipartFiles) {
		this.multipartFiles = multipartFiles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (companyId ^ (companyId >>> 32));
		result = prime * result + ((imgName == null) ? 0 : imgName.hashCode());
		result = prime * result + ((info == null) ? 0 : info.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((moreInfo == null) ? 0 : moreInfo.hashCode());
		result = prime * result + ((multipartFiles == null) ? 0 : multipartFiles.hashCode());
		result = prime * result + ((phone_number == null) ? 0 : phone_number.hashCode());
		result = prime * result + price;
		result = prime * result + (int) (spaceId ^ (spaceId >>> 32));
		result = prime * result + ((spaceName == null) ? 0 : spaceName.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		SpaceInfoVO other = (SpaceInfoVO) obj;
		if (companyId != other.companyId)
			return false;
		if (imgName == null) {
			if (other.imgName != null)
				return false;
		} else if (!imgName.equals(other.imgName))
			return false;
		if (info == null) {
			if (other.info != null)
				return false;
		} else if (!info.equals(other.info))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (moreInfo == null) {
			if (other.moreInfo != null)
				return false;
		} else if (!moreInfo.equals(other.moreInfo))
			return false;
		if (multipartFiles == null) {
			if (other.multipartFiles != null)
				return false;
		} else if (!multipartFiles.equals(other.multipartFiles))
			return false;
		if (phone_number == null) {
			if (other.phone_number != null)
				return false;
		} else if (!phone_number.equals(other.phone_number))
			return false;
		if (price != other.price)
			return false;
		if (spaceId != other.spaceId)
			return false;
		if (spaceName == null) {
			if (other.spaceName != null)
				return false;
		} else if (!spaceName.equals(other.spaceName))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SpaceInfoVO [spaceId=" + spaceId + ", companyId=" + companyId + ", spaceName=" + spaceName + ", info="
				+ info + ", moreInfo=" + moreInfo + ", type=" + type + ", location=" + location + ", price=" + price
				+ ", phone_number=" + phone_number + ", imgName=" + imgName + ", multipartFiles=" + multipartFiles
				+ "]";
	}

	
	

	
}