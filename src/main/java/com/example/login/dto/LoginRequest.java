package com.example.login.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequest {

	// 用戶名稱/帳號
    @JsonProperty("user_name")
    private String userName;
    // 密碼
    private String password;
    // 身分
    private String role;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
