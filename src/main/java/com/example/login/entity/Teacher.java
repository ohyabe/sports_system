package com.example.login.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// 定義教師實體，對應資料庫中的表格
@Entity
@Table(name = "access_management_teacher")
public class Teacher {

	// 教師帳號
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "teacher_id")
//	@JsonProperty("teacher_id")
	private String teacherId;

	// 學年度(參加運動會)
	@Column(name = "academic_year")
//	@JsonProperty("academic_year")
	private String academicYear;

	// 教師班級
	@Column(name = "cla")
//	@JsonProperty("cla")
	private String cla;

	// 教師姓名
	@Column(name = "teacher_name")
//	@JsonProperty("teacher_name")
	private String teacherName;

	// 教師密碼
	@Column(name = "teacher_password")
//	@JsonProperty("teacher_password")
	private String teacherPassword;

	// 教師信箱
	@Column(name = "teacher_email")
//	@JsonProperty("teacher_email")
	private String teacherEmail;

	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Teacher(String teacherId, String academicYear, String cla, String teacherName, String teacherPassword,
			String teacherEmail) {
		super();
		this.teacherId = teacherId;
		this.academicYear = academicYear;
		this.cla = cla;
		this.teacherName = teacherName;
		this.teacherPassword = teacherPassword;
		this.teacherEmail = teacherEmail;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
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

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getTeacherPassword() {
		return teacherPassword;
	}

	public void setTeacherPassword(String teacherPassword) {
		this.teacherPassword = teacherPassword;
	}

	public String getTeacherEmail() {
		return teacherEmail;
	}

	public void setTeacherEmail(String teacherEmail) {
		this.teacherEmail = teacherEmail;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
}
