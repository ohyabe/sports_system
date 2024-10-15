package com.example.login.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.login.entity.TournamentManagement;





public interface TournamentManagementRepository extends JpaRepository<TournamentManagement, Long> {
    @Query("SELECT t.courseItem FROM TournamentManagement t")
    List<String> findAllCourseItems();
}

