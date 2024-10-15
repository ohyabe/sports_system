package com.example.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.login.entity.ContactMessage;

public interface ContactMessageDao extends JpaRepository<ContactMessage, Long> {
}
