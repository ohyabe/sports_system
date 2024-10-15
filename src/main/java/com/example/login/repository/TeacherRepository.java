package com.example.login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.login.entity.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findByTeacherId(String teacherId);

    Teacher findByTeacherNameAndTeacherPassword(String teacherName, String teacherPassword);
    
    @Query("SELECT t.cla FROM Teacher t WHERE t.teacherName = :teacherName AND t.teacherPassword = :teacherPassword")
    String findClassByTeacherNameAndTeacherPassword(@Param("teacherName") String teacherName, @Param("teacherPassword") String teacherPassword);

    

}