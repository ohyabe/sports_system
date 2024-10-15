package com.example.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.login.entity.Image;



@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
