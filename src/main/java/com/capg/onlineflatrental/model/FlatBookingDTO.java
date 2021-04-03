package com.capg.onlineflatrental.model;

import java.util.Date;
import org.springframework.stereotype.Component;
import com.capg.onlineflatrental.entities.Flat;
import com.capg.onlineflatrental.entities.Tenant;

@Component
public class FlatBookingDTO {

	private int	bookingNo;
	private Flat flat;
	private Tenant tenant;
	private Date bookingFromDate;
	private Date bookingToDate;
	
	public FlatBookingDTO() {
		super();
	}

	public FlatBookingDTO(int bookingNo, Flat flat, Tenant tenant, Date bookingFromDate, Date bookingToDate) {
		super();
		this.bookingNo = bookingNo;
		this.flat = flat;
		this.tenant = tenant;
		this.bookingFromDate = bookingFromDate;
		this.bookingToDate = bookingToDate;
	}

	public int getBookingNo() {
		return bookingNo;
	}

	public void setBookingNo(int bookingNo) {
		this.bookingNo = bookingNo;
	}

	public Flat getFlat() {
		return flat;
	}

	public void setFlat(Flat flat) {
		this.flat = flat;
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public Date getBookingFromDate() {
		return bookingFromDate;
	}

	public void setBookingFromDate(Date bookingFromDate) {
		this.bookingFromDate = bookingFromDate;
	}

	public Date getBookingToDate() {
		return bookingToDate;
	}

	public void setBookingToDate(Date bookingToDate) {
		this.bookingToDate = bookingToDate;
	}

	@Override
	public String toString() {
		return "FlatBooking [bookingNo=" + bookingNo + ", flat=" + flat + ", tenantId=" + tenant
				+ ", bookingFromDate=" + bookingFromDate + ", bookingToDate=" + bookingToDate + "]";
	}
	
}
