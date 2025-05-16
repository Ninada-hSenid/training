package com.trainingprogram.student_api.controller;

import com.trainingprogram.student_api.dto.request.StudentRequest;
import com.trainingprogram.student_api.dto.resonse.StudentResponse;
import com.trainingprogram.student_api.exception.StudentNotFoundException;
import com.trainingprogram.student_api.model.Student;
import com.trainingprogram.student_api.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        logger.info("Fetching all students");
        List<StudentResponse> students = studentService.getAllStudents().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        logger.info("Successfully fetched {} students", students.size());
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable String id) {
        logger.info("Fetching student with id: {}", id);
        return studentService.getStudentById(id)
                .map(student -> {
                    logger.info("Student with id {} found", id);
                    return ResponseEntity.ok(mapToResponse(student));
                })
                .orElseThrow(() -> {
                    logger.error("Student with id {} not found", id);
                    return new StudentNotFoundException(id);
                });
    }

    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(@RequestBody StudentRequest request) {
        logger.info("Creating new student with name: {}", request.getName());
        Student student = mapToEntity(request);
        Student createdStudent = studentService.createStudent(student);
        logger.info("Student with id {} created successfully", createdStudent.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToResponse(createdStudent));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> updateStudent(@PathVariable String id, @RequestBody StudentRequest request) {
        logger.info("Updating student with id: {}", id);
        return studentService.updateStudent(id, mapToEntity(request))
                .map(updatedStudent -> {
                    logger.info("Student with id {} updated successfully", id);
                    return ResponseEntity.ok(mapToResponse(updatedStudent));
                })
                .orElseThrow(() -> {
                    logger.warn("Attempt to update non-existent student with id: {}", id);
                    return new StudentNotFoundException(id);
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String id) {
        logger.info("Deleting student with id: {}", id);
        try {
            studentService.deleteStudent(id);
            logger.info("Student with id {} deleted successfully", id);
        } catch (StudentNotFoundException ex) {
            logger.error("Failed to delete. Student with id {} not found", id);
            throw ex;
        }
        return ResponseEntity.noContent().build();
    }

    // Mapper methods
    private StudentResponse mapToResponse(Student student) {
        StudentResponse response = new StudentResponse();
        response.setId(student.getId());
        response.setName(student.getName());
        response.setDateOfBirth(student.getDateOfBirth());
        response.setAverage(student.getAverage());
        return response;
    }

    private Student mapToEntity(StudentRequest request) {
        Student student = new Student();
        student.setName(request.getName());
        student.setDateOfBirth(request.getDateOfBirth());
        student.setAverage(request.getAverage());
        return student;
    }
}
