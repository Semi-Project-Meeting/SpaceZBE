package com.spaceZ.serviceInfo;

import org.springframework.web.multipart.MultipartFile;

public class ImagesVO {
	private long imagesId;
	private long spaceId;
	private String imgName;
	
	public ImagesVO() {
		// TODO Auto-generated constructor stub
	}
	
	public ImagesVO(long imagesId, long spaceId, String imgName) {
		super();
		this.imagesId = imagesId;
		this.spaceId = spaceId;
		this.imgName = imgName;
	}

	public long getImagesId() {
		return imagesId;
	}
	public void setImagesId(long imageId) {
		this.imagesId = imageId;
	}
	public long getSpaceId() {
		return spaceId;
	}
	public void setSpaceId(long spaceId) {
		this.spaceId = spaceId;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	
	
}
