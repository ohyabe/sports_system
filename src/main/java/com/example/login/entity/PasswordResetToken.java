package com.example.login.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//存儲重置密碼 Token 的數據庫表的目的是增加安全性和控制力，防止未經授權的密碼重置操作。以下是原因：
//
//驗證 Token 的有效性：
//
//當用戶點擊電子郵件中的重置密碼連結時，後端可以檢查 Token 是否存在且未過期。
//如果不存儲 Token，無法驗證該 Token 是否有效或是否被惡意使用。
//設置 Token 的有效期：
//
//通過存儲 Token，可以為其設置有效期，過期的 Token 將無法使用，增加了安全性。
//防止用戶長期使用舊的重置密碼連結。
//防止多次使用同一 Token：
//
//一旦使用 Token 成功重置密碼，可以將其從數據庫中刪除，確保每個 Token 只能使用一次。
//防止重複使用已經使用過的 Token 來進行密碼重置操作。
//追踪和審計：
//
//可以記錄每個 Token 的生成時間、用戶信息和使用狀態，便於追踪和審計。
//有助於發現和應對潛在的安全問題。
@Entity
@Table(name = "password_reset_token")
public class PasswordResetToken {

	@Id
	@Column(name = "token")
	private String token;

	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "role")
	private String role;
	
	@Column(name = "expiry_date")
	private LocalDateTime expiryDate;

	public PasswordResetToken() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PasswordResetToken(String token, String userId, String role, LocalDateTime expiryDate) {
		super();
		this.token = token;
		this.userId = userId;
		this.role = role;
		this.expiryDate = expiryDate;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
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

	public LocalDateTime getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDateTime expiryDate) {
		this.expiryDate = expiryDate;
	}

}
