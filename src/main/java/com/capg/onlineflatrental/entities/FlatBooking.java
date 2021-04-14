package com.capg.onlineflatrental.entities;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FlatBooking")
public class FlatBooking {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BOOKING_NUMBER")
	private int	bookingNo;
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "flatId")
	private Flat flat;
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "tenantId")
	private Tenant tenant;
	@Column(name = "BOOKING_FROM_DATE")
	private LocalDate bookingFromDate;
	@Column(name = "BOOKING_TO_DATE")
	private LocalDate bookingToDate;
	
	public FlatBooking() {
		super();
	}

	public FlatBooking(int bookingNo, Flat flat, Tenant tenant, LocalDate bookingFromDate, LocalDate bookingToDate) {
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
		return "FlatBooking [bookingNo=" + bookingNo + ", flat=" + flat + ", tenantId=" + tenant
				+ ", bookingFromDate=" + bookingFromDate + ", bookingToDate=" + bookingToDate + "]";
	}
	
}
