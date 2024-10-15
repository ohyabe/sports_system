package com.example.login.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.login.entity.Student;



@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByStudentId(String studentId);
    
    @Query("SELECT s FROM Student s WHERE s.cla = (SELECT t.cla FROM Teacher t WHERE t.teacherName = :teacherName AND t.teacherPassword = :teacherPassword)")
    List<Student> findStudentsByTeacherNameAndTeacherPassword(@Param("teacherName") String teacherName, @Param("teacherPassword") String teacherPassword);

    @Query("SELECT s FROM Student s WHERE s.cla = :cla")
    List<Student> findStudentsByClass(@Param("cla") String cla);

    List<Student> findAll();
}
