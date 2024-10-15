package com.example.login.service.ifs;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.login.entity.CourseImage;
import com.example.login.entity.Image;
import com.example.login.repository.ImageRepository;
import com.example.login.repository.TournamentManagementRepository;



@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private TournamentManagementRepository tournamentManagementRepository;

    @Override
    public String uploadImage(MultipartFile file) {
        try {
            Image image = new Image();
            image.setData(file.getBytes());
            imageRepository.save(image);
            return "圖片上傳成功";
        } catch (IOException e) {
            e.printStackTrace();
            return "圖片上傳失敗";
        }
    }

      @Override
    public List<CourseImage> getAllCourseImages() {
        List<Image> images = imageRepository.findAll();
        List<String> courseItems = tournamentManagementRepository.findAllCourseItems();

        return IntStream.range(0, Math.min(images.size(), courseItems.size()))
                .mapToObj(i -> new CourseImage(
                        courseItems.get(i), 
                        Base64.getEncoder().encodeToString(images.get(i).getData())))
                .collect(Collectors.toList());
    }
    @Override
    public CourseImage updateImage(MultipartFile file, String courseItem) throws IOException {
        
        List<Image> images = imageRepository.findAll();
        List<String> courseItems = tournamentManagementRepository.findAllCourseItems();
        int index = courseItems.indexOf(courseItem);

        if (index == -1 || index >= images.size()) {
            throw new RuntimeException("項目未找到");
        }

        // 更新圖片
        Image image = images.get(index);
        image.setData(file.getBytes());
        imageRepository.save(image);

        String base64Image = Base64.getEncoder().encodeToString(image.getData());
        return new CourseImage(courseItem, base64Image);
    }
    
}
