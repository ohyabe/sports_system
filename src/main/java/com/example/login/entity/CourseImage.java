package com.example.login.entity;

public class CourseImage {
    private String courseItem;
    private String base64Image;

    public CourseImage(String courseItem, String base64Image) {
        this.courseItem = courseItem;
        this.base64Image = base64Image;
    }

    public String getCourseItem() {
        return courseItem;
    }

    public void setCourseItem(String courseItem) {
        this.courseItem = courseItem;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }
}
