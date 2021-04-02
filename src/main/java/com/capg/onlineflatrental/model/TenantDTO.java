package com.capg.onlineflatrental.model;

import org.springframework.stereotype.Component;
import com.capg.onlineflatrental.entities.FlatAddress;
import com.capg.onlineflatrental.entities.FlatBooking;

@Component
public class TenantDTO {

	private int tenantId;
	private int tenantAge;
	private FlatAddress tenantAddress;
	private FlatBooking flatBooking;
	
	public TenantDTO() {
		super();
	}

	public TenantDTO(int tenantId, int tenantAge, FlatAddress tenantAddress, FlatBooking flatBooking) {
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
