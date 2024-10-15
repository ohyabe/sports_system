package com.example.login.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChangePasswordRequest {

	@JsonProperty("user_id")
	private String userId;

	private String role;

	@JsonProperty("user_email")
	private String userEmail;

	@JsonProperty("new_password")
	private String newPassword;

	@JsonProperty("token")
	private String token;

	public ChangePasswordRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChangePasswordRequest(String userId, String role, String userEmail, String newPassword, String token) {
		super();
		this.userId = userId;
		this.role = role;
		this.userEmail = userEmail;
		this.newPassword = newPassword;
		this.token = token;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
