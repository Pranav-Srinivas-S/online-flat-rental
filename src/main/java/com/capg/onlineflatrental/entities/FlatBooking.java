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
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "FlatBooking")
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
	@Column(name = "BOOKING_FROM_DATE")
	@NotBlank(message = "Booking From Date is mandatory")
	private LocalDate bookingFromDate;
	@Column(name = "BOOKING_TO_DATE", nullable = false)
	@NotBlank(message = "Booking To Date is mandatory")
	private LocalDate bookingToDate;
	
	public FlatBooking() {
		super();
	}

	public FlatBooking(int bookingNo, Flat flat, Tenant tenant, @NotBlank(message = "Booking From Date is mandatory") @NotBlank(message = "Booking From Date is mandatory") @NotBlank(message = "Booking From Date is mandatory") @NotBlank(message = "Booking From Date is mandatory") @NotBlank(message = "Booking From Date is mandatory") LocalDate bookingFromDate, @NotBlank(message = "Booking To Date is mandatory") @NotBlank(message = "Booking To Date is mandatory") @NotBlank(message = "Booking To Date is mandatory") @NotBlank(message = "Booking To Date is mandatory") @NotBlank(message = "Booking To Date is mandatory") LocalDate bookingToDate) {
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

	public @NotBlank(message = "Booking From Date is mandatory") LocalDate getBookingFromDate() {
		return bookingFromDate;
	}

	public void setBookingFromDate(@NotBlank(message = "Booking From Date is mandatory") LocalDate bookingFromDate) {
		this.bookingFromDate = bookingFromDate;
	}

	public @NotBlank(message = "Booking To Date is mandatory") LocalDate getBookingToDate() {
		return bookingToDate;
	}

	public void setBookingToDate(@NotBlank(message = "Booking To Date is mandatory") @NotBlank(message = "Booking To Date is mandatory") @NotBlank(message = "Booking To Date is mandatory") @NotBlank(message = "Booking To Date is mandatory") @NotBlank(message = "Booking To Date is mandatory") LocalDate bookingToDate) {
		this.bookingToDate = bookingToDate;
	}

	@Override
	public String toString() {
		return "FlatBooking [bookingNo=" + bookingNo + ", flat=" + flat + ", tenantId=" + tenant
				+ ", bookingFromDate=" + bookingFromDate + ", bookingToDate=" + bookingToDate + "]";
	}

	
}
