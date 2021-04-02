package com.capg.onlineflatrental.entities;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Tenant {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int tenantId;
	private int tenantAge;
	@Embedded
	private FlatAddress tenantAddress;
	@OneToOne(cascade = CascadeType.ALL)
	private FlatBooking flatBooking;
	
	public Tenant() {
		super();
	}

	public Tenant(int tenantId, int tenantAge, FlatAddress tenantAddress, FlatBooking flatBooking) {
		super();
		this.tenantId = tenantId;
		this.tenantAge = tenantAge;
		this.tenantAddress = tenantAddress;
		this.flatBooking = flatBooking;
	}

	public int getTenantId() {
		return tenantId;
	}

	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	public int getTenantAge() {
		return tenantAge;
	}

	public void setTenantAge(int tenantAge) {
		this.tenantAge = tenantAge;
	}

	public FlatAddress getTenantAddress() {
		return tenantAddress;
	}

	public void setTenantAddress(FlatAddress tenantAddress) {
		this.tenantAddress = tenantAddress;
	}

	public FlatBooking getFlatBooking() {
		return flatBooking;
	}

	public void setFlatBooking(FlatBooking flatBooking) {
		this.flatBooking = flatBooking;
	}

	@Override
	public String toString() {
		return "Tenant [tenantId=" + tenantId + ", tenantAge=" + tenantAge + ", tenantAddress=" + tenantAddress
				+ ", flatBooking=" + flatBooking + "]";
	}
	
}
