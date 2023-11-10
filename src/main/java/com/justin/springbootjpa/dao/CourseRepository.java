package com.justin.springbootjpa.dao;

import com.justin.springbootjpa.entity.Course;
import com.justin.springbootjpa.entity.Instructor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course,Integer> {

    List<Course> findByInstructorId(int id);

    Course findByTitle(String title);
}
