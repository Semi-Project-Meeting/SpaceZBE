package com.spaceZ.reply;

public class ReplyVO {

	private long replyId;
	private long spaceId;
	private long memberId;
	private String content;
	private String replyDate;
	public ReplyVO() {
	}
	public ReplyVO(long replyId, long spaceId, long memberId, String content, String replyDate) {
		this.replyId = replyId;
		this.spaceId = spaceId;
		this.memberId = memberId;
		this.content = content;
		this.replyDate = replyDate;
	}
	public long getReplyId() {
		return replyId;
	}
	public void setReplyId(long replyId) {
		this.replyId = replyId;
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
		result = prime * result + (int) (memberId ^ (memberId >>> 32));
		result = prime * result + ((replyDate == null) ? 0 : replyDate.hashCode());
		result = prime * result + (int) (replyId ^ (replyId >>> 32));
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
		return "ReplyVO [replyId=" + replyId + ", spaceId=" + spaceId + ", memberId=" + memberId + ", content="
				+ content + ", replyDate=" + replyDate + "]";
	}
	
}
