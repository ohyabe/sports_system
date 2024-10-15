package com.example.login.service.ifs;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.login.entity.Teacher;
import com.example.login.repository.TeacherRepository;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Optional<Teacher> getTeacherById(Long id) {
        return teacherRepository.findById(id);
    }

    public Teacher createTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Teacher updateTeacher(Long id, Teacher teacherDetails) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        if (optionalTeacher.isPresent()) {
            Teacher teacher = optionalTeacher.get();
            teacher.setAcademicYear(teacherDetails.getAcademicYear());
            teacher.setCla(teacherDetails.getCla());
            teacher.setTeacherName(teacherDetails.getTeacherName());
            teacher.setTeacherPassword(teacherDetails.getTeacherPassword());
            teacher.setTeacherEmail(teacherDetails.getTeacherEmail());
            teacher.setTeacherId(teacherDetails.getTeacherId());
            return teacherRepository.save(teacher);
        } else {
            return null; // 或者拋出一個自定義的異常
        }
    }

    public void deleteTeacher(Long id) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        if (optionalTeacher.isPresent()) {
            teacherRepository.delete(optionalTeacher.get());
        } else {
            // 處理找不到老師的情況
        }
    }

}

