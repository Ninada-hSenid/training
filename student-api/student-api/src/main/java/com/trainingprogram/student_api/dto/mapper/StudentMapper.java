package com.trainingprogram.student_api.dto.mapper;

import com.trainingprogram.student_api.dto.request.StudentRequest;
import com.trainingprogram.student_api.dto.resonse.StudentResponse;
import com.trainingprogram.student_api.model.Student;

public class StudentMapper {
    public static Student toEntity(StudentRequest request) {
        Student student = new Student();
        student.setName(request.getName());
        student.setDateOfBirth(request.getDateOfBirth());
        student.setAverage(request.getAverage());
        return student;
    }

    public static StudentResponse toResponse(Student student) {
        StudentResponse response = new StudentResponse();
        response.setId(student.getId());
        response.setName(student.getName());
        response.setDateOfBirth(student.getDateOfBirth());
        response.setAverage(student.getAverage());
        return response;
    }

    public static void updateEntity(Student existingStudent, StudentRequest request) {
        existingStudent.setName(request.getName());
        existingStudent.setDateOfBirth(request.getDateOfBirth());
        existingStudent.setAverage(request.getAverage());
    }
}
