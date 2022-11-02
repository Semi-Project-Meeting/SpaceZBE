package com.spaceZ.payment;

public class RefundVO {

	private String merchant_uid;
	private String reason;
	private int cancel_request_amount;
	private long memberId;

	public RefundVO() {
		// TODO Auto-generated constructor stub
	}

	public RefundVO(int price) {
		this.cancel_request_amount = price;
	}

	public RefundVO(String merchant_uid, String reason, int cancel_request_amount, long memberId) {
		this.merchant_uid = merchant_uid;
		this.reason = reason;
		this.cancel_request_amount = cancel_request_amount;
		this.memberId = memberId;
	}

	public String getMerchant_uid() {
		return merchant_uid;
	}

	public void setMerchant_uid(String merchant_uid) {
		this.merchant_uid = merchant_uid;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getCancel_request_amount() {
		return cancel_request_amount;
	}

	public void setCancel_request_amount(int cancel_request_amount) {
		this.cancel_request_amount = cancel_request_amount;
	}

	public long getMemberId() {
		return memberId;
	}

	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "RefundVO [merchant_uid=" + merchant_uid + ", reason=" + reason + ", cancel_request_amount="
				+ cancel_request_amount + ", memberId=" + memberId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cancel_request_amount;
		result = prime * result + (int) (memberId ^ (memberId >>> 32));
		result = prime * result + ((merchant_uid == null) ? 0 : merchant_uid.hashCode());
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
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
		RefundVO other = (RefundVO) obj;
		if (cancel_request_amount != other.cancel_request_amount)
			return false;
		if (memberId != other.memberId)
			return false;
		if (merchant_uid == null) {
			if (other.merchant_uid != null)
				return false;
		} else if (!merchant_uid.equals(other.merchant_uid))
			return false;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		return true;
	}

}
