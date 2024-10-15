package com.example.login.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// 定義學生實體，對應資料庫中的表格
@Entity
@Table(name = "access_management_student") 
public class Student {

    // 學生學號帳號
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "student_id")
    private String studentId;

    // 學年度(參加運動會)
    @Column(name = "academic_year")
    private String academicYear;

    // 學生班級
    @Column(name = "cla")
    private String cla;

    // 學生姓名
    @Column(name = "student_name")
    private String studentName;

    // 學生密碼
    @Column(name = "student_password")
    private String studentPassword;

    // 學生信箱
    @Column(name = "student_email")
    private String studentEmail;

    public Student() {
        super();
    }

    public Student(String studentId, String academicYear, String cla, String studentName, String studentPassword, String studentEmail) {
        super();
        this.studentId = studentId;
        this.academicYear = academicYear;
        this.cla = cla;
        this.studentName = studentName;
        this.studentPassword = studentPassword;
        this.studentEmail = studentEmail;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public String getCla() {
        return cla;
    }

    public void setCla(String cla) {
        this.cla = cla;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }
}
