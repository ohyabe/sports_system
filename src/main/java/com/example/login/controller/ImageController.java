package com.example.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.login.entity.CourseImage;
import com.example.login.service.ifs.ImageService;

@RestController
@RequestMapping("/upload")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        return imageService.uploadImage(file);
    }
     @GetMapping("/course-images")
    public List<CourseImage> getAllCourseImages() {
        return imageService.getAllCourseImages();
    }
     @PostMapping("/update-image")
    public ResponseEntity<CourseImage> updateImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("courseItem") String courseItem) {
        try {
            CourseImage updatedImage = imageService.updateImage(file, courseItem);
            return ResponseEntity.ok(updatedImage);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}