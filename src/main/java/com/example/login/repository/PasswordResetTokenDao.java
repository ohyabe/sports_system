package com.example.login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.login.entity.PasswordResetToken;

public interface PasswordResetTokenDao extends JpaRepository<PasswordResetToken, String>{

	public Optional<PasswordResetToken> findByToken(String token);
    public void deleteByToken(String token);
}
