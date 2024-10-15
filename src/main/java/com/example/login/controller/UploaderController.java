package com.example.login.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import com.example.login.entity.Album;
import com.example.login.entity.Photo;
import com.example.login.service.ifs.PhotoService;
import com.example.loginvo.ResponseResult;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/business")
@CrossOrigin
public class UploaderController extends BaseController {

    @Autowired
    private PhotoService photoService;

    @ResponseBody
    @RequestMapping("/uploadAlbum")
    public ResponseResult uploadAlbum(
            @RequestParam("albumName") String albumName,
            @RequestParam(value = "files", required = false) MultipartFile[] files,
            @RequestParam("uploaderName") String uploaderName,
            @RequestParam("uploaderRole") String uploaderRole,
            HttpServletRequest request, HttpServletResponse response) {
        ResponseResult result = new ResponseResult();
        List<String> urls = new ArrayList<>();
        int code = 1;

        if (files != null && files.length > 0) {
            long totalSize = 0;
            for (MultipartFile file : files) {
                totalSize += file.getSize();
            }
            if (totalSize > 50 * 1024 * 1024) { // 確保總大小不超過 50MB
                result.setMessage("文件總大小超過限制");
                result.setCode(code);
                return result;
            }

            String returnUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                    + request.getContextPath() + "/upload/imgs/"; // 儲存路徑
            String path = request.getSession().getServletContext().getRealPath("upload/imgs"); // 文件儲存位置
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String fileAdd = sdf.format(new Date());
            File dir = new File(path + "/" + fileAdd);

            if (!dir.exists() && !dir.isDirectory()) {
                dir.mkdirs(); // 這裡使用 mkdirs() 來確保目錄和其父目錄的創建
            }

            for (MultipartFile file : files) {
                String fileName = file.getOriginalFilename(); // 獲取文件名加後綴
                if (fileName != null && !fileName.isEmpty()) {
                    String fileF = fileName.substring(fileName.lastIndexOf(".")); // 文件後綴
                    fileName = new Date().getTime() + "_" + new Random().nextInt(1000) + fileF; // 新的文件名
                    File targetFile = new File(dir, fileName);

                    try {
                        file.transferTo(targetFile);
                        String url = returnUrl + fileAdd + "/" + fileName;
                        urls.add(url);
                        // 保存圖片信息到相簿，增加上傳者姓名和身分
                        photoService.savePhoto(albumName, url, fileName, uploaderName, uploaderRole);
                        code = 0;
                    } catch (Exception e) {
                        e.printStackTrace();
                        result.setMessage("系統異常，圖片上傳失敗");
                        result.setCode(code);
                        return result;
                    }
                }
            }

            result.setCode(code);
            result.setMessage("圖片上傳成功");
            Map<String, Object> map = new HashMap<>();
            map.put("urls", urls);
            result.setResult(map);
        } else {
            result.setMessage("沒有上傳的文件");
            result.setCode(code);
        }

        return result;
    }

    @ResponseBody
    @RequestMapping("/deleteAlbum")
    public ResponseResult deleteAlbum(@RequestParam("albumName") String albumName) {
        ResponseResult result = new ResponseResult();
        boolean success = photoService.deleteAlbum(albumName);
        if (success) {
            result.setCode(0);
            result.setMessage("相簿刪除成功");
        } else {
            result.setCode(1);
            result.setMessage("相簿刪除失敗");
        }
        return result;
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public void handleMaxSizeException(MaxUploadSizeExceededException exc, HttpServletResponse response) {
        ResponseResult result = new ResponseResult();
        result.setCode(1);
        result.setMessage("文件大小超過限制");
        writeJson(response, result);
    }

    @ResponseBody
    @RequestMapping("/getAlbumsWithPhotos")
    public List<Map<String, Object>> getAlbumsWithPhotos() {
        List<Map<String, Object>> result = new ArrayList<>();
        for (Album album : photoService.getAllAlbums()) {
            Map<String, Object> albumMap = new HashMap<>();
            albumMap.put("name", album.getName());
            albumMap.put("uploaderName", album.getUploaderName()); // 返回上傳者姓名
            albumMap.put("uploaderRole", album.getUploaderRole()); // 返回上傳者身份
            List<Map<String, Object>> photos = new ArrayList<>();
            for (Photo photo : album.getPhotos()) {
                Map<String, Object> photoMap = new HashMap<>();
                photoMap.put("id", photo.getId());
                photoMap.put("url", photo.getUrl());
                photoMap.put("title", photo.getTitle());
                photoMap.put("uploaderName", photo.getUploaderName());
                photoMap.put("uploaderRole", photo.getUploaderRole());
                photos.add(photoMap);
            }
            albumMap.put("photos", photos);
            result.add(albumMap);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/getPhotos")
    public List<Map<String, Object>> getPhotos(@RequestParam("albumName") String albumName) {
        List<Map<String, Object>> result = new ArrayList<>();
        for (Photo photo : photoService.getPhotos(albumName)) {
            Map<String, Object> photoMap = new HashMap<>();
            photoMap.put("id", photo.getId());
            photoMap.put("url", photo.getUrl());
            photoMap.put("title", photo.getTitle());
            photoMap.put("uploaderName", photo.getUploaderName());
            photoMap.put("uploaderRole", photo.getUploaderRole());
            result.add(photoMap);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/deletePhoto")
    public ResponseResult deletePhoto(@RequestParam("albumName") String albumName, @RequestParam("url") String url) {
        ResponseResult result = new ResponseResult();
        boolean success = photoService.deletePhoto(albumName, url);
        if (success) {
            result.setCode(0);
            result.setMessage("照片刪除成功");
        } else {
            result.setCode(1);
            result.setMessage("照片文件刪除失敗");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/updateAlbumName") // 修改相簿名稱
    public ResponseResult updateAlbumName(@RequestBody Map<String, String> request) {
        String oldName = request.get("oldName");
        String newName = request.get("newName");
        String currentUserName = request.get("uploaderName"); // 從請求中獲取當前使用者名稱
        ResponseResult result = new ResponseResult();

        try {
            boolean isUploader = photoService.isUploader(oldName, currentUserName); // 驗證是否為上傳者
            if (!isUploader) {
                result.setCode(1);
                result.setMessage("非上傳者無法修改");
                return result;
            }

            boolean success = photoService.updateAlbumName(oldName, newName);
            if (success) {
                result.setCode(0);
                result.setMessage("相簿名稱修改成功");
            } else {
                result.setCode(1);
                result.setMessage("相簿名稱修改失敗");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(1);
            result.setMessage("系統異常，請稍後再試");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/uploadPhotosToAlbum")
    public ResponseResult uploadPhotosToAlbum(
            @RequestParam("albumName") String albumName,
            @RequestParam(value = "files", required = false) MultipartFile[] files,
            @RequestParam("uploaderName") String uploaderName,
            @RequestParam("uploaderRole") String uploaderRole,
            HttpServletRequest request, HttpServletResponse response) {
        ResponseResult result = new ResponseResult();
        List<String> urls = new ArrayList<>();
        int code = 1;

        if (files != null && files.length > 0) {
            long totalSize = 0;
            for (MultipartFile file : files) {
                totalSize += file.getSize();
            }
            if (totalSize > 50 * 1024 * 1024) { // 確保總大小不超過 50MB
                result.setMessage("文件總大小超過限制");
                result.setCode(code);
                return result;
            }

            String returnUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                    + request.getContextPath() + "/upload/imgs/"; // 儲存路徑
            String path = request.getSession().getServletContext().getRealPath("upload/imgs"); // 文件儲存位置
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String fileAdd = sdf.format(new Date());
            File dir = new File(path + "/" + fileAdd);

            if (!dir.exists() && !dir.isDirectory()) {
                dir.mkdirs(); // 這裡使用 mkdirs() 來確保目錄和其父目錄的創建
            }

            for (MultipartFile file : files) {
                String fileName = file.getOriginalFilename(); // 獲取文件名加後綴
                if (fileName != null && !fileName.isEmpty()) {
                    String fileF = fileName.substring(fileName.lastIndexOf(".")); // 文件後綴
                    fileName = new Date().getTime() + "_" + new Random().nextInt(1000) + fileF; // 新的文件名
                    File targetFile = new File(dir, fileName);

                    try {
                        file.transferTo(targetFile);
                        String url = returnUrl + fileAdd + "/" + fileName;
                        urls.add(url);
                        // 保存圖片信息到相簿
                        photoService.savePhoto(albumName, url, fileName, uploaderName, uploaderRole);
                        code = 0;
                    } catch (Exception e) {
                        e.printStackTrace();
                        result.setMessage("系統異常，圖片上傳失敗");
                        result.setCode(code);
                        return result;
                    }
                }
            }

            result.setCode(code);
            result.setMessage("圖片上傳成功");
            Map<String, Object> map = new HashMap<>();
            map.put("urls", urls);
            result.setResult(map);
        } else {
            result.setMessage("沒有上傳的文件");
            result.setCode(code);
        }

        return result;
    }

}