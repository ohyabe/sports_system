package com.example.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.login.entity.Album;

public interface AlbumRepositoryDao extends JpaRepository<Album, Long> {
    Album findByName(String name);
}