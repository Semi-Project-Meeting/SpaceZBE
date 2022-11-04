package com.spaceZ.mileage;

public class MileageVO {

	private long mileageId;
	private long memberId;
	private long spaceId;
	private String spaceName;
	private int score;
	private String mileage_date;
	private String status;

	public MileageVO() {
		// TODO Auto-generated constructor stub
	}

	public MileageVO(long mileageId, long memberId, long spaceId, String spaceName, int score, String mileage_date,
			String status) {
		super();
		this.mileageId = mileageId;
		this.memberId = memberId;
		this.spaceId = spaceId;
		this.spaceName = spaceName;
		this.score = score;
		this.mileage_date = mileage_date;
		this.status = status;
	}

	public long getMileageId() {
		return mileageId;
	}

	public void setMileageId(long mileageId) {
		this.mileageId = mileageId;
	}

	public long getMemberId() {
		return memberId;
	}

	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}

	public long getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(long spaceId) {
		this.spaceId = spaceId;
	}

	public String getSpaceName() {
		return spaceName;
	}

	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getMileage_date() {
		return mileage_date;
	}

	public void setMileage_date(String mileage_date) {
		this.mileage_date = mileage_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "MileageVO [mileageId=" + mileageId + ", memberId=" + memberId + ", spaceId=" + spaceId + ", spaceName="
				+ spaceName + ", score=" + score + ", mileage_date=" + mileage_date + ", status=" + status + "]";
	}

	

}
