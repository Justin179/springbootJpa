package com.justin.springbootjpa.dao;

import com.justin.springbootjpa.entity.Instructor;
import com.justin.springbootjpa.entity.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student,Integer> {
    Student findByEmail(String email);
}
