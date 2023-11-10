package com.justin.springbootjpa;

import com.justin.springbootjpa.dao.AppDAO;
import com.justin.springbootjpa.entity.Course;
import com.justin.springbootjpa.entity.Instructor;
import com.justin.springbootjpa.entity.InstructorDetail;
import com.justin.springbootjpa.entity.Review;
import com.justin.springbootjpa.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SpringbootJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJpaApplication.class, args);
    }

    /*
        1 每個單測都是獨立的，彼此不可以互相影響。
        2 單元測試的結果，不受外部服務影響，因為是在測試程式的邏輯有沒有被改壞。
        -> 總之如果程式沒動到，每次跑的結果都要是一樣的
     */
    @Bean
    CommandLineRunner commandLineRunner(AppDAO appDAO){
        return runner -> { // will be executed right after the service started

            // one to one
//            findInstructor(appDAO);  // test written, tw hereafter

//            createInstructor(appDAO); // tw
//
//            deleteInstructor(appDAO);
//
//            findInstructorDetail(appDAO);
//
//            deleteInstructorDetail(appDAO);


            // one to many (one instructor has many courses)
//            createInstructorWithCourses(appDAO);
//
//            findInstructorWithCourses(appDAO);
//
//            findInstructorWithCoursesByJoinFetch(appDAO);
//
//            updateInstructor(appDAO);
//
//            updateCourse(appDAO);
//
//            deleteCourseById(appDAO);


            // one to many (one course has many reviews)
//            createCourseAndReviews(appDAO);
//
//            retrieveCourseAndReviews(appDAO);

//            deleteCourseAndReviews(appDAO); // 沒找到，是沒寫嗎?

            // many to many
//            createCourseAndStudents(appDAO);
//
//            retrieveCourseAndStudents(appDAO);
//
//            createStudentAndCourses(appDAO);
//
//            retrieveStudentAndCourses(appDAO);
//
//            addMoreCoursesForStudent(appDAO);
//
//            deleteStudentById(appDAO);

        };
    }

    private void findInstructor(AppDAO appDAO) {
        int id = 4;
        Instructor instructor = appDAO.findInstructorById(id);
        System.out.println(instructor);
        System.out.println(instructor.getInstructorDetail());
    }

    private void createInstructor(AppDAO appDAO) {
//        Instructor instructor = new Instructor("Chad", "Darby", "darby@luv2code.com");
//        InstructorDetail instructorDetail = new InstructorDetail("http://www.luv2code.com/youtube", "Luv 2 code!!!");

        Instructor instructor = new Instructor("Madhu", "Patel", "madhu@luv2code.com");
        InstructorDetail instructorDetail = new InstructorDetail("http://www.luv2code.com/youtube", "Guitar");
        instructor.setInstructorDetail(instructorDetail);

        appDAO.save(instructor);
    }

    private void deleteInstructor(AppDAO appDAO) {
        int id = 4;
        appDAO.deleteInstructorById(id);
    }

    private void findInstructorDetail(AppDAO appDAO) {
        int id = 2;
        InstructorDetail instructorDetail = appDAO.findInstructorDetailById(id);
        Instructor instructor = instructorDetail.getInstructor();
        System.out.println(instructorDetail);
        System.out.println(instructor);
    }

    private void deleteInstructorDetail(AppDAO appDAO) {
        int id = 3;
        appDAO.deleteInstructorDetailById(id);
    }

    private void createInstructorWithCourses(AppDAO appDAO) {

        Instructor instructor = new Instructor("Susan", "Public", "susan.public@gmail.com");
        InstructorDetail instructorDetail = new InstructorDetail("http://www.youtube.com", "Video Games");
        instructor.setInstructorDetail(instructorDetail);

        // add courses to the instructor
        Course course = new Course("Air Guitar - The Ultimate Guide");
        Course course2 = new Course("The Pinball Masterclass");
        instructor.add(course);
        instructor.add(course2);

        // save the instructor and its courses and its detail
        appDAO.save(instructor);
    }

    private void findInstructorWithCourses(AppDAO appDAO) {
        int id = 4;
        Instructor instructor = appDAO.findInstructorById(id);
        List<Course> courses = appDAO.findCoursesByInstructorId(id);
        instructor.setCourses(courses);

        System.out.println(instructor);
        System.out.println(instructor.getCourses());
    }

    private void findInstructorWithCoursesByJoinFetch(AppDAO appDAO) {
        int id = 4;
        Instructor instructor = appDAO.findInstructorByIdJoinFetch(id);

        System.out.println(instructor);
        System.out.println(instructor.getCourses());
    }

    private void updateInstructor(AppDAO appDAO) {
        int id = 4;
        Instructor instructor = appDAO.findInstructorById(id);
        instructor.setLastName("RE2");
        appDAO.update(instructor);
    }

    private void updateCourse(AppDAO appDAO) {
        int courseId = 10;
        Course course = appDAO.findCourseById(courseId);
        course.setTitle("RE4");
        appDAO.update(course);
    }

    private void deleteCourseById(AppDAO appDAO) {
        int id = 5;
        appDAO.deleteCourseById(id);
    }

    private void createCourseAndReviews(AppDAO appDAO) {
        Course course = new Course("how to aim & shoot");
        course.addReview(new Review("well written"));
        course.addReview(new Review("best guide ever"));
        course.addReview(new Review("excellent"));
        appDAO.save(course);
    }

    private void retrieveCourseAndReviews(AppDAO appDAO) {
        int id = 12;
        Course course = appDAO.findCourseAndReviewsByCourseId(id);
        System.out.println(course);
        System.out.println(course.getReviews());
    }

    private void createCourseAndStudents(AppDAO appDAO) {
        Course course = new Course("how to properly aim");
        Student student = new Student("john", "doe", 33);
        Student student2 = new Student("Mary", "public", 44);
        course.addStudent(student);
        course.addStudent(student2);
        appDAO.save(course);
    }

    private void retrieveCourseAndStudents(AppDAO appDAO) {
        int courseId = 1;
        Course course = appDAO.findCourseAndStudentsByCourseId(courseId);
        System.out.println(course);
        System.out.println(course.getStudents());
    }

    private void createStudentAndCourses(AppDAO appDAO) {
        Student student = new Student("Ashley", "ashley@gmail.com", 20);
        Course course = new Course("RE2 guide");
        Course course2 = new Course("RE4 guide");
        student.addCourse(course);
        student.addCourse(course2);
        appDAO.save(student);
    }

    private void retrieveStudentAndCourses(AppDAO appDAO) {
        int studentId = 3;
        Student student = appDAO.findStudentAndCoursesByStudentId(studentId);
        System.out.println(student);
        System.out.println(student.getCourses());
    }

    private void addMoreCoursesForStudent(AppDAO appDAO) {
        int studentId = 3;
        Student student = appDAO.findStudentAndCoursesByStudentId(studentId);
        student.addCourse(new Course("Rubik's cube - how to speed cube"));
        student.addCourse(new Course("Atari 2600 - Game Development"));

        appDAO.update(student);

        System.out.println(student);
        System.out.println(student.getCourses());
    }

    private void deleteStudentById(AppDAO appDAO) {
        int studentId = 3;
        appDAO.deleteStudentById(studentId);
        System.out.println("deleted student id: "+studentId);
    }

}
