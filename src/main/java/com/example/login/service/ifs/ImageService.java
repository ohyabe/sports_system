package com.example.login.service.ifs;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.login.entity.CourseImage;


public interface ImageService {
    String uploadImage(MultipartFile file);
    List<CourseImage> getAllCourseImages(); // 返回Base64編碼的圖片數據
    CourseImage updateImage(MultipartFile file, String courseItem) throws IOException;
}
