package com.example.login.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.login.entity.IndividualPerformance;

public interface IndividualRepository extends JpaRepository<IndividualPerformance, Integer> {
    List<IndividualPerformance> findByItemId(int itemId);

    IndividualPerformance findByIdAndItemId(int id, int itemId);
    
    List<IndividualPerformance> findByClasses(String classes);
    
    List<IndividualPerformance> findByBreakRecord(String breakRecord);
}
