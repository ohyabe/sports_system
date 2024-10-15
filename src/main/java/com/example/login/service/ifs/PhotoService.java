package com.example.login.service.ifs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.login.entity.Album;
import com.example.login.entity.Photo;
import com.example.login.repository.AlbumRepositoryDao;
import com.example.login.repository.PhotoRepositoryDao;

@Service
public class PhotoService {

    @Autowired
    private AlbumRepositoryDao albumRepository;

    @Autowired
    private PhotoRepositoryDao photoRepository;

    public void savePhoto(String albumName, String url, String title, String uploaderName, String uploaderRole) {
        Album album = albumRepository.findByName(albumName);
        if (album == null) {
            album = new Album();
            album.setName(albumName);
            album.setUploaderName(uploaderName); // 保存上傳者姓名
            album.setUploaderRole(uploaderRole); // 保存上傳者身份
            albumRepository.save(album);
        }
        Photo photo = new Photo();
        photo.setUrl(url);
        photo.setTitle(title);
        photo.setAlbum(album);
        photo.setUploaderName(uploaderName); // 保存上傳者姓名
        photo.setUploaderRole(uploaderRole); // 保存上傳者身份
        photoRepository.save(photo);
    }

    public List<Photo> getPhotos(String albumName) {
        Album album = albumRepository.findByName(albumName);
        if (album != null) {
            return photoRepository.findByAlbum(album);
        }
        return List.of();
    }

    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    public boolean deletePhoto(String albumName, String url) {
        Album album = albumRepository.findByName(albumName);
        if (album != null) {
            Photo photo = photoRepository.findByUrl(url);
            if (photo != null) {
                photoRepository.delete(photo);
                return true;
            }
        }
        return false;
    }

    public boolean deleteAlbum(String albumName) {
        Album album = albumRepository.findByName(albumName);
        if (album != null) {
            albumRepository.delete(album);
            return true;
        }
        return false;
    }

    public boolean updateAlbumName(String oldName, String newName) {
        Album album = albumRepository.findByName(oldName);
        if (album != null) {
            album.setName(newName);
            albumRepository.save(album);
            return true;
        }
        return false;
    }

    public boolean isUploader(String albumName, String uploaderName) {
        Album album = albumRepository.findByName(albumName);
        return album != null && album.getUploaderName().equals(uploaderName);
    }

    public boolean isPhotoUploader(String albumName, String photoUrl, String uploaderName) {
        Photo photo = photoRepository.findByAlbumNameAndUrl(albumName, photoUrl);
        return photo != null && photo.getUploaderName().equals(uploaderName);
    }

}
