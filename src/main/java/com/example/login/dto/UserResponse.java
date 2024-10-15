package com.example.login.dto;

public class UserResponse {
    // 使用者姓名
    private String name;

    // 使用者身分
    private String role;

    // 構造函數，用於初始化 name 和 role
    public UserResponse(String name, String role) {
        this.name = name;
        this.role = role;
    }

    // 獲取用戶姓名的方法
    public String getName() {
        return name;
    }

    // 獲取用戶角色的方法
    public String getRole() {
        return role;
    }
}
