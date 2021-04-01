package com.capg.onlineflatrental.model;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class FlatBookingDTO {

	private int	bookingNo;
	private FlatDTO flat;
	private TenantDTO tenantId;
	private LocalDate bookingFromDate;
	private LocalDate bookingToDate;
	
	public FlatBookingDTO() {
		super();
	}

	public FlatBookingDTO(int bookingNo, FlatDTO flat, TenantDTO tenantId, LocalDate bookingFromDate, LocalDate bookingToDate) {
		super();
		this.bookingNo = bookingNo;
		this.flat = flat;
		this.tenantId = tenantId;
		this.bookingFromDate = bookingFromDate;
		this.bookingToDate = bookingToDate;
	}

	public int getBookingNo() {
		return bookingNo;
	}

	public void setBookingNo(int bookingNo) {
		this.bookingNo = bookingNo;
	}

	public FlatDTO getFlat() {
		return flat;
	}

	public void setFlat(FlatDTO flat) {
		this.flat = flat;
	}

	public TenantDTO getTenantId() {
		return tenantId;
	}

	public void setTenantId(TenantDTO tenantId) {
		this.tenantId = tenantId;
	}

	public LocalDate getBookingFromDate() {
		return bookingFromDate;
	}

	public void setBookingFromDate(LocalDate bookingFromDate) {
		this.bookingFromDate = bookingFromDate;
	}

	public LocalDate getBookingToDate() {
		return bookingToDate;
	}

	public void setBookingToDate(LocalDate bookingToDate) {
		this.bookingToDate = bookingToDate;
	}

	@Override
	public String toString() {
		return "FlatBooking [bookingNo=" + bookingNo + ", flat=" + flat + ", tenantId=" + tenantId
				+ ", bookingFromDate=" + bookingFromDate + ", bookingToDate=" + bookingToDate + "]";
	}
	
}
