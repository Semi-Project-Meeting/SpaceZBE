package com.spaceZ.review;

public class ReviewVO {

	private int reviewId;
	private int spaceId;
	private int memberId;
	private String content;
	private int rating;
	private String reviewDate;
	public ReviewVO() {
	}
	public ReviewVO(int reviewId, int spaceId, int memberId, String content, int rating, String reviewDate) {
		this.reviewId = reviewId;
		this.spaceId = spaceId;
		this.memberId = memberId;
		this.content = content;
		this.rating = rating;
		this.reviewDate = reviewDate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + memberId;
		result = prime * result + rating;
		result = prime * result + ((reviewDate == null) ? 0 : reviewDate.hashCode());
		result = prime * result + reviewId;
		result = prime * result + spaceId;
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
		ReviewVO other = (ReviewVO) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (memberId != other.memberId)
			return false;
		if (rating != other.rating)
			return false;
		if (reviewDate == null) {
			if (other.reviewDate != null)
				return false;
		} else if (!reviewDate.equals(other.reviewDate))
			return false;
		if (reviewId != other.reviewId)
			return false;
		if (spaceId != other.spaceId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ReviewVO [reviewId=" + reviewId + ", spaceId=" + spaceId + ", memberId=" + memberId + ", content="
				+ content + ", rating=" + rating + ", reviewDate=" + reviewDate + "]";
	}
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	public int getSpaceId() {
		return spaceId;
	}
	public void setSpaceId(int spaceId) {
		this.spaceId = spaceId;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
	
	
}
