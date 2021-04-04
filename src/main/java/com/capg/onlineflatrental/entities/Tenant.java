package com.capg.onlineflatrental.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Entity
//@Table(name = "Tenant")
public class Tenant {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int tenantId;
	@Column(name = "TENANT_AGE")
	@NotBlank(message = "Tenant Age is mandatory")
	private int tenantAge;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="addressId")
	private FlatAddress tenantAddress;
	
	public Tenant() {
		super();
	}

	public Tenant(int tenantId, int tenantAge, FlatAddress tenantAddress) {
		super();
		this.tenantId = tenantId;
		this.tenantAge = tenantAge;
		this.tenantAddress = tenantAddress;
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


	@Override
	public String toString() {
		return "Tenant [tenantId=" + tenantId + ", tenantAge=" + tenantAge + ", tenantAddress=" + tenantAddress
				+ "]";
	}
	
}
