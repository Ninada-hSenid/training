package com.trainingprogram.student_api.repository;

import com.trainingprogram.student_api.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {

}
