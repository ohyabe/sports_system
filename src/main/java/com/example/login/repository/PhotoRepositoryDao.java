package com.example.login.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.login.entity.Album;
import com.example.login.entity.Photo;

public interface PhotoRepositoryDao extends JpaRepository<Photo, Long> {
	public void deleteByUrl(String url);

	public Photo findByUrl(String url);

	public List<Photo> findByAlbum(Album album);

}
