package com.example.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.login.entity.EventSchedule;



@Repository
public interface EventScheduleRepository extends JpaRepository<EventSchedule, Long> {
}
