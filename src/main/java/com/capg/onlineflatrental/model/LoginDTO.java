package com.capg.onlineflatrental.model;

import org.springframework.stereotype.Component;

@Component
public class LoginDTO {

	private int loginId;
	private String loginPassword;
	
	public LoginDTO() {
		super();
	}

	public LoginDTO(int loginId, String loginPassword) {
		super();
		this.loginId = loginId;
		this.loginPassword = loginPassword;
	}

	public int getLoginId() {
		return loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	

	@Override
	public String toString() {
		return "LoginDTO [loginId=" + loginId + ", loginPassword=" + loginPassword + "]";
	}

}