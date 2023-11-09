package com.justin.springbootjpa.dao;


import com.justin.springbootjpa.entity.Course;
import com.justin.springbootjpa.entity.Instructor;
import com.justin.springbootjpa.entity.InstructorDetail;
import com.justin.springbootjpa.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{

    private EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class,id);
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {
        TypedQuery<Instructor> query = entityManager.createQuery(
                "select i from Instructor i " +
                        "JOIN FETCH i.courses " +
                        "JOIN FETCH i.instructorDetail " +
                        "where i.id = :id"
                , Instructor.class);
        query.setParameter("id",id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor instructor = entityManager.find(Instructor.class, id);

        List<Course> courses = instructor.getCourses();
        for (Course course:courses){
            course.setInstructor(null);
        }

        entityManager.remove(instructor);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
        Course course = entityManager.find(Course.class, id);
        entityManager.remove(course);
    }

    @Override
    @Transactional
    public void deleteStudentById(int id) {
        Student student = entityManager.find(Student.class, id);
        entityManager.remove(student);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, id);
        return instructorDetail;
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, id);

        // remove the associated object reference to break the bi-directional link
        instructorDetail.getInstructor().setInstructorDetail(null);

        entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {
        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id=:id",Course.class);
        query.setParameter("id",id);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }
    @Override
    @Transactional
    public void update(Course course) {
        entityManager.merge(course);
    }
    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    public Course findCourseById(int id) {
//        TypedQuery<Course> query = entityManager.createQuery("from Course where id=:id", Course.class);
//        Course course = query.getSingleResult();
//        return course;
        return entityManager.find(Course.class,id);
    }


    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor); // future updates to the entity will be tracked.
    }
    @Override
    @Transactional
    public void save(Course course) {
        entityManager.persist(course); // future updates to the entity will be tracked.
    }
    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student); // future updates to the entity will be tracked.
    }


    @Override
    public Student findStudentAndCoursesByStudentId(int id) {
        TypedQuery<Student> query = entityManager.createQuery("select s from Student s " +
                "JOIN FETCH s.courses " +
                "where s.id = :id", Student.class);
        query.setParameter("id",id);

        Student student = query.getSingleResult();

        return student;
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int id) {

        TypedQuery<Course> query = entityManager.createQuery("select c from Course c " +
                "JOIN FETCH c.reviews " +
                "where c.id = :id", Course.class);
        query.setParameter("id",id);

        Course course = query.getSingleResult();

        return course;
    }

    @Override
    public Course findCourseAndStudentsByCourseId(int id) {

        TypedQuery<Course> query = entityManager.createQuery("select c from Course c " +
                "JOIN FETCH c.students " +
                "where c.id = :id", Course.class);
        query.setParameter("id",id);

        Course course = query.getSingleResult();

        return course;
    }
}
