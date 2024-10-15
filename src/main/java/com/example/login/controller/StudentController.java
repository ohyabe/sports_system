package com.example.login.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.entity.Student;
import com.example.login.repository.StudentRepository;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/byClass")
    public List<Student> getStudentsByClass(@RequestParam("teacherName") String teacherName, @RequestParam("teacherPassword") String teacherPassword) {
        return studentRepository.findStudentsByTeacherNameAndTeacherPassword(teacherName, teacherPassword);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Student> getStudentById(@PathVariable Long id) {
        return studentRepository.findById(id);
    }

    @PutMapping("/{id}")
public Student updateStudent(@PathVariable("id") Long id, @RequestBody Student studentDetails) {
    System.out.println("Received update request for student ID: " + id);
    System.out.println("Received student details for update: " + studentDetails);
    
    return studentRepository.findById(id)
        .map(student -> {
            student.setAcademicYear(studentDetails.getAcademicYear());
            student.setCla(studentDetails.getCla());
            student.setStudentName(studentDetails.getStudentName());
            student.setStudentPassword(studentDetails.getStudentPassword());
            student.setStudentEmail(studentDetails.getStudentEmail());
            student.setStudentId(studentDetails.getStudentId());
            Student updatedStudent = studentRepository.save(student);
            System.out.println("Updated student data: " + updatedStudent);
            return updatedStudent;
        })
        .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
}

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @DeleteMapping
    public void deleteStudent(@RequestParam("id") Long id) {
        studentRepository.deleteById(id);
    }
}
