package com.capg.onlineflatrental.entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
//@Table(name = "Login")
public class Login {
	
	@Id
	@Column(name = "LOGIN_ID", nullable = false)
	private int loginId;
	
	@Column(name = "LOGIN_PASSWORD", length = 20)
	@NotBlank(message = "Login Password cannot be blank")
	
	private String loginPassword;
	
	public Login() {
		super();
	}

	public Login(int loginId, String loginPassword) {
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
		return "Login [lohinId=" + loginId + ", loginPassword=" + loginPassword + "]";
	}
	
}
