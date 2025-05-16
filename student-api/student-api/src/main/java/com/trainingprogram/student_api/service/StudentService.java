package com.trainingprogram.student_api.service;

import com.trainingprogram.student_api.exception.StudentNotFoundException;
import com.trainingprogram.student_api.model.Student;
import com.trainingprogram.student_api.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(String id) {
        return studentRepository.findById(id);
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(String id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException(id);
        }
        studentRepository.deleteById(id);
    }
    public Optional<Student> updateStudent(String id, Student student) {
        return studentRepository.findById(id).map(existingStudent -> {
            existingStudent.setName(student.getName());
            existingStudent.setDateOfBirth(student.getDateOfBirth());
            existingStudent.setAverage(student.getAverage());
            return studentRepository.save(existingStudent);
        });
    }


}
