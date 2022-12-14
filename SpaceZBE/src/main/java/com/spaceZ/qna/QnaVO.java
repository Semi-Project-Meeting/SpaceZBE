package com.spaceZ.qna;

public class QnaVO {

	private long qnaId;
	private long spaceId;
	private long memberId;
	private String title;
	private String content;
	private String qnaDate;
	private String answer;
	public QnaVO() {
	}
	public QnaVO(long qnaId, long spaceId, long memberId, String title, String content, String qnaDate, String answer) {
		this.qnaId = qnaId;
		this.spaceId = spaceId;
		this.memberId = memberId;
		this.title = title;
		this.content = content;
		this.qnaDate = qnaDate;
		this.answer = answer;
	}
	public long getQnaId() {
		return qnaId;
	}
	public void setQnaId(long qnaId) {
		this.qnaId = qnaId;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getQnaDate() {
		return qnaDate;
	}
	public void setQnaDate(String qnaDate) {
		this.qnaDate = qnaDate;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answer == null) ? 0 : answer.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + (int) (memberId ^ (memberId >>> 32));
		result = prime * result + ((qnaDate == null) ? 0 : qnaDate.hashCode());
		result = prime * result + (int) (qnaId ^ (qnaId >>> 32));
		result = prime * result + (int) (spaceId ^ (spaceId >>> 32));
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		QnaVO other = (QnaVO) obj;
		if (answer == null) {
			if (other.answer != null)
				return false;
		} else if (!answer.equals(other.answer))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (memberId != other.memberId)
			return false;
		if (qnaDate == null) {
			if (other.qnaDate != null)
				return false;
		} else if (!qnaDate.equals(other.qnaDate))
			return false;
		if (qnaId != other.qnaId)
			return false;
		if (spaceId != other.spaceId)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "QnaVO [qnaId=" + qnaId + ", spaceId=" + spaceId + ", memberId=" + memberId + ", title=" + title
				+ ", content=" + content + ", qnaDate=" + qnaDate + ", answer=" + answer + "]";
	}
	
}
