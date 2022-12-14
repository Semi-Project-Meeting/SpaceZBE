package com.spaceZ.review;

public class ReviewVO {

	private long reviewId;
	private long spaceId;
	private long memberId;
	private String content;
	private int rating;
	private String reviewDate;
	public ReviewVO() {
	}
	public ReviewVO(long reviewId, long spaceId, long memberId, String content, int rating, String reviewDate) {
		this.reviewId = reviewId;
		this.spaceId = spaceId;
		this.memberId = memberId;
		this.content = content;
		this.rating = rating;
		this.reviewDate = reviewDate;
	}
	public long getReviewId() {
		return reviewId;
	}
	public void setReviewId(long reviewId) {
		this.reviewId = reviewId;
	}
	public long getSpaceId() {
		return spaceId;
	}
	public void setSpaceId(long spaceId) {
		this.spaceId = spaceId;
	}
	public long getMemberId() {
		return memberId;
	}
	public void setMemberId(long memberId) {
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + (int) (memberId ^ (memberId >>> 32));
		result = prime * result + rating;
		result = prime * result + ((reviewDate == null) ? 0 : reviewDate.hashCode());
		result = prime * result + (int) (reviewId ^ (reviewId >>> 32));
		result = prime * result + (int) (spaceId ^ (spaceId >>> 32));
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
	
	
}
