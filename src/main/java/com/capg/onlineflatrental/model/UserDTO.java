package com.capg.onlineflatrental.model;

import org.springframework.stereotype.Component;

@Component
public class UserDTO {

	private int userId;
	private String userName;
	private String password;
	private String userType;
	
	public UserDTO() {
		super();
	}

	public UserDTO(int userId, String userName, String password, String userType) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.userType = userType;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", userType=" + userType
				+ "]";
	}

	public static UserDTO get() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
