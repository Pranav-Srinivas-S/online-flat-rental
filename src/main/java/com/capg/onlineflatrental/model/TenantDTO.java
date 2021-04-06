package com.capg.onlineflatrental.model;

import org.springframework.stereotype.Component;
import com.capg.onlineflatrental.entities.FlatAddress;

@Component
public class TenantDTO {

	private int tenantId;
	private String tenantName;
	private int tenantAge;
	private FlatAddress tenantAddress;
	
	public TenantDTO() {
		super();
	}

	public TenantDTO(int tenantId, String tenantName, int tenantAge, FlatAddress tenantAddress) {
		super();
		this.tenantId = tenantId;
		this.tenantName = tenantName;
		this.tenantAge = tenantAge;
		this.tenantAddress = tenantAddress;
	}

	public int getTenantId() {
		return tenantId;
	}

	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
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

	@Override
	public String toString() {
		return "Tenant [tenantId=" + tenantId + ", TenantName=" + tenantName + ", tenantAge=" + tenantAge
				+ ", tenantAddress=" + tenantAddress + "]";
	}
	
}
