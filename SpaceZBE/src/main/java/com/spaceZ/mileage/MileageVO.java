package com.spaceZ.mileage;

public class MileageVO {

	private long mileageId;
	private long memberId;
	private String spaceName;
	private int score;
	private String mileage_date;
	private String status;

	public MileageVO() {
		// TODO Auto-generated constructor stub
	}

	public MileageVO(long mileageId, long memberId, String spaceName, int score, String mileage_date, String status) {
		super();
		this.mileageId = mileageId;
		this.memberId = memberId;
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
		return "MileageVO [mileageId=" + mileageId + ", memberId=" + memberId + ", spaceName=" + spaceName + ", score="
				+ score + ", mileage_date=" + mileage_date + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (memberId ^ (memberId >>> 32));
		result = prime * result + (int) (mileageId ^ (mileageId >>> 32));
		result = prime * result + ((mileage_date == null) ? 0 : mileage_date.hashCode());
		result = prime * result + score;
		result = prime * result + ((spaceName == null) ? 0 : spaceName.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		MileageVO other = (MileageVO) obj;
		if (memberId != other.memberId)
			return false;
		if (mileageId != other.mileageId)
			return false;
		if (mileage_date == null) {
			if (other.mileage_date != null)
				return false;
		} else if (!mileage_date.equals(other.mileage_date))
			return false;
		if (score != other.score)
			return false;
		if (spaceName == null) {
			if (other.spaceName != null)
				return false;
		} else if (!spaceName.equals(other.spaceName))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

}
