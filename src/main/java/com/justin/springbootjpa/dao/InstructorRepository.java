package com.justin.springbootjpa.dao;

import com.justin.springbootjpa.entity.Instructor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InstructorRepository extends CrudRepository<Instructor,Integer> {

    Instructor findByEmail(String email);
}
