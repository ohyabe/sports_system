package com.example.login.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "individual_performance")
public class IndividualPerformance {
	
	@Column(name = "academic_year")
	private String academicYear;
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "historical_performance")
	private String historicalPerformance;

	@Column(name = "item_id")
	private int itemId;

	@Column(name = "course_item")
	private String courseItem;

	@Column(name = "pre_match")
	private String preMatch;

	@Column(name = "classes")
	private String classes;

	@Column(name = "student_name")
	private String studentName;

	@Column(name = "project_performance")
	private String projectPerformance;
	

	@Column(name = "break_record")
	private String breakRecord;

	@Column(name = "ranking")
	private String ranking;
	
	
	@Column(name = "project_score")
	private Integer projectScore;

	@Column(name = "total_score")
	private Integer totalScore;

	

	// Getters and Setters
	
	
	public String getAcademicYear() {
		return academicYear;
	}
	
	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHistoricalPerformance() {
		return historicalPerformance;
	}

	public void setHistoricalPerformance(String historicalPerformance) {
		this.historicalPerformance = historicalPerformance;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getCourseItem() {
		return courseItem;
	}

	public void setCourseItem(String courseItem) {
		this.courseItem = courseItem;
	}

	public String getPreMatch() {
		return preMatch;
	}

	public void setPreMatch(String preMatch) {
		this.preMatch = preMatch;
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getProjectPerformance() {
		return projectPerformance;
	}

	public void setProjectPerformance(String projectPerformance) {
		this.projectPerformance = projectPerformance;
	}
	
	public String getBreakRecord() {
		return breakRecord;
	}

	public void setBreakRecord(String breakRecord) {
		this.breakRecord = breakRecord;
	}

	public String getRanking() {
		return ranking;
	}

	public void setRanking(String ranking) {
		this.ranking = ranking;
	}

	public Integer getProjectScore() {
		return projectScore;
	}

	public void setProjectScore(Integer projectScore) {
		this.projectScore = projectScore;
	}

	public Integer getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}
}
