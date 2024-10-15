package com.example.login.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// 定義管理員實體，對應資料庫中的表格
@Entity
@Table(name = "access_management_admin")
public class Admin {

	// 管理員帳號
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "admin_id")
	// @JsonProperty("admin_id")
	private String adminId;

	// 管理員姓名
	@Column(name = "admin_name")
	// @JsonProperty("admin_name")
	private String adminName;

	// 管理員密碼
	@Column(name = "admin_password")
	// @JsonProperty("admin_password")
	private String adminPassword;

	// 管理員信箱
	@Column(name = "admin_email")
	// @JsonProperty("admin_email")
	private String adminEmail;

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(String adminId, String adminName, String adminPassword, String adminEmail) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminPassword = adminPassword;
		this.adminEmail = adminEmail;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

}
