package com.spaceZ.reservation;

public class ReservationVO {
	private long reservationId;  // 예약번호
	private long spaceId; // 사무공간 번호
	private long memberId; // 회원 번호
	private String startDate; // 공간 대여 시작날짜 시간  
	private String endDate; // 공간 대여 끝 날짜 시간 
	private String status; // 예약 상태
	private String payStatus; // 결제 상태
	private int price; // 이용 가격
	private String prepay; // 선결제 or 보증금결제 
	
	public ReservationVO() {
		
	}
	
	public ReservationVO(long reservationId, long spaceId, long memberId, String startDate, String endDate,
			String status, String payStatus, int price, String prepay) {
		super();
		this.reservationId = reservationId;
		this.spaceId = spaceId;
		this.memberId = memberId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.payStatus = payStatus;
		this.price = price;
		this.prepay = prepay;
	}

	public long getReservationId() {
		return reservationId;
	}

	public void setReservationId(long reservationId) {
		this.reservationId = reservationId;
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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getPrepay() {
		return prepay;
	}

	public void setPrepay(String prepay) {
		this.prepay = prepay;
	}
	
	@Override
	public String toString() {
		return "ReservationVO [reservationId=" + reservationId + ", spaceId=" + spaceId + ", memberId=" + memberId
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", status=" + status + ", payStatus="
				+ payStatus + ", price=" + price + ", prepay=" + prepay + "]";
	}
	
	
}
