package com.capg.onlineflatrental.model;

import org.springframework.stereotype.Component;

@Component
public class AdminDTO {
	
	private int adminId;
	private String adminPassword;
	
	public AdminDTO() {
		super();
	}

	public AdminDTO(int adminId, String adminPassword) {
		super();
		this.adminId = adminId;
		this.adminPassword = adminPassword;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminPassword=" + adminPassword + "]";
	}
	
}
