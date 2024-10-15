package com.example.login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.login.entity.Admin;


public interface AdminRepository extends JpaRepository<Admin, Long> {
    public Optional<Admin> findByAdminId(String adminId);
}
