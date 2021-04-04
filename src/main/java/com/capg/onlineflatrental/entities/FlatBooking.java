package com.capg.onlineflatrental.entities;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

@Entity
//@Table(name = "FlatBooking")
public class FlatBooking {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BOOKING_NUMBER")
	private int	bookingNo;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "flatId")
	private Flat flat;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tenantId")
	private Tenant tenant;
	@Temporal(TemporalType.DATE)
	@Column(name = "BOOKING_FROM_DATE")
	@NotBlank(message = "Booking From Date is mandatory")
	private Date bookingFromDate;
	@Temporal(TemporalType.DATE)
	@Column(name = "BOOKING_TO_DATE", nullable = false)
	@NotBlank(message = "Booking To Date is mandatory")
	private Date bookingToDate;
	
	public FlatBooking() {
		super();
	}

	public FlatBooking(int bookingNo, Flat flat, Tenant tenant, Date bookingFromDate, Date bookingToDate) {
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
