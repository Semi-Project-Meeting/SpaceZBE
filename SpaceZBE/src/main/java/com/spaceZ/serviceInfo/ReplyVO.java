package com.spaceZ.serviceInfo;

public class ReplyVO {

	private int replyId;
	private int spaceId;
	private int memberId;
	private String content;
	private String replyDate;
	public ReplyVO() {
	}
	public ReplyVO(int replyId, int spaceId, int memberId, String content, String replyDate) {
		this.replyId = replyId;
		this.spaceId = spaceId;
		this.memberId = memberId;
		this.content = content;
		this.replyDate = replyDate;
	}
	public int getReplyId() {
		return replyId;
	}
	public void setReplyId(int replyId) {
		this.replyId = replyId;
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
	public String getReplyDate() {
		return replyDate;
	}
	public void setReplyDate(String replyDate) {
		this.replyDate = replyDate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + memberId;
		result = prime * result + ((replyDate == null) ? 0 : replyDate.hashCode());
		result = prime * result + replyId;
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
		ReplyVO other = (ReplyVO) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (memberId != other.memberId)
			return false;
		if (replyDate == null) {
			if (other.replyDate != null)
				return false;
		} else if (!replyDate.equals(other.replyDate))
			return false;
		if (replyId != other.replyId)
			return false;
		if (spaceId != other.spaceId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "replyId [replyId=" + replyId + ", spaceId=" + spaceId + ", memberId=" + memberId + ", content="
				+ content + ", replyDate=" + replyDate + "]";
	}
	
	
}
