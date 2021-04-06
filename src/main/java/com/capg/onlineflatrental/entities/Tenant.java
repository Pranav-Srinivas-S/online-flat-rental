package com.capg.onlineflatrental.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Tenant")
public class Tenant {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int tenantId;
	@Column(name = "TEANANT_NAME", length = 30)
	@NotBlank(message = "Tenant Name is mandatory")
	private String tenantName;
	@Column(name = "TENANT_AGE")
	private int tenantAge;
	@OneToOne(cascade = CascadeType.ALL)
	private FlatAddress tenantAddress;
	
	public Tenant() {
		super();
	}

	public Tenant(int tenantId, String tenantName, int tenantAge, FlatAddress tenantAddress) {
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
