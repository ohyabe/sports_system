//package com.example.login.entity;
//
//import java.io.Serializable;
//import java.util.Objects;
//
//public class UserLoginId implements Serializable {
//    private Long id;
//    private String userName;
//
//    // 默認構造方法
//    public UserLoginId() {}
//
//    // 帶參數的構造方法
//    public UserLoginId(Long id, String userName) {
//        this.id = id;
//        this.userName = userName;
//    }
//
//    // getters and setters
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        UserLoginId that = (UserLoginId) o;
//        return Objects.equals(id, that.id) && Objects.equals(userName, that.userName);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, userName);
//    }
//}
