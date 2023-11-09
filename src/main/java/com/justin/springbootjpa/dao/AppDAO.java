package com.justin.springbootjpa.dao;

import com.justin.springbootjpa.entity.Course;
import com.justin.springbootjpa.entity.Instructor;
import com.justin.springbootjpa.entity.InstructorDetail;
import com.justin.springbootjpa.entity.Student;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AppDAO {
    void save(Instructor instructor);

    Instructor findInstructorById(int id);

    Instructor findInstructorByIdJoinFetch(int id);

    void deleteInstructorById(int id);

    void deleteCourseById(int id);
    void deleteStudentById(int id);

    InstructorDetail findInstructorDetailById(int id);

    void deleteInstructorDetailById(int id);

    List<Course> findCoursesByInstructorId(int id);

    void update(Instructor instructor);

    void update(Course course);

    @Transactional
    void update(Student student);

    Course findCourseById(int id);

    Course findCourseAndReviewsByCourseId(int id);

    // from a course to students
    void save(Course course);
    Course findCourseAndStudentsByCourseId(int id);

    // from a student to courses
    void save(Student course);

    Student findStudentAndCoursesByStudentId(int id);


}
