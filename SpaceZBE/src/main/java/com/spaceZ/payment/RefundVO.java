package com.spaceZ.payment;

public class RefundVO {

	private String imp_uid;
	private String reason;
	private int cancel_request_amount;
	
	
	public String getImp_uid() {
		return imp_uid;
	}
	public void setImp_uid(String imp_uid) {
		this.imp_uid = imp_uid;
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
	
	@Override
	public String toString() {
		return "RefundVO [imp_uid=" + imp_uid + ", reason=" + reason + ", cancel_request_amount="
				+ cancel_request_amount + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cancel_request_amount;
		result = prime * result + ((imp_uid == null) ? 0 : imp_uid.hashCode());
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
		if (imp_uid == null) {
			if (other.imp_uid != null)
				return false;
		} else if (!imp_uid.equals(other.imp_uid))
			return false;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		return true;
	}
	
	
	
}
