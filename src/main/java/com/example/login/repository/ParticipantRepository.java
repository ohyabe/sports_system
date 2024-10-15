package com.example.login.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.login.entity.Participant;



@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    List<Participant> findByStudentClass(String studentClass);
}