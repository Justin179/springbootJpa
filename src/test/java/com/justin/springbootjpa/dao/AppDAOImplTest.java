package com.justin.springbootjpa.dao;

import com.justin.springbootjpa.entity.Course;
import com.justin.springbootjpa.entity.Instructor;
import com.justin.springbootjpa.entity.InstructorDetail;
import com.justin.springbootjpa.entity.Review;
import com.justin.springbootjpa.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AppDAOImplTest {

    @Autowired
    private AppDAO appDAO;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;


    @Test
    void findInstructor() {
        int id = 4;
        Instructor instructor = appDAO.findInstructorById(id);
        assertEquals(instructor.getFirstName(),"Madhu");
        assertEquals(instructor.getInstructorDetail().getHobby(),"Guitar");
    }

    @Test
    @Transactional
    void createInstructor() {
        Instructor instructor = new Instructor("Titanota", "Patel", "Titanota@gmail.com");
        InstructorDetail instructorDetail = new InstructorDetail("http://www.luv2code.com/youtube", "Guitar");
        instructor.setInstructorDetail(instructorDetail);
        appDAO.save(instructor);

        Instructor titanota = instructorRepository.findByEmail("Titanota@gmail.com");
        assertNotNull(titanota);
    }

    @Test
    @Transactional
    void deleteInstructor() {
        int id = 4;
        Instructor instructor = appDAO.findInstructorById(id);
        assertNotNull(instructor);
        appDAO.deleteInstructorById(id);
        instructor = appDAO.findInstructorById(id);
        assertNull(instructor);
    }

    @Test
    void findInstructorDetail(){
        int instructorDetailId = 1;
        InstructorDetail instructorDetail = appDAO.findInstructorDetailById(instructorDetailId);
        Instructor instructor = instructorDetail.getInstructor();
        assertNotNull(instructorDetail);
        assertNotNull(instructor);
    }
    /*
    Instructor @OneToOne(cascade = CascadeType.ALL) InstructorDetail
    InstructorDetail @OneToOne(mappedBy = "instructorDetail",
            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}) Instructor
    也就是說，在刪除instructorDetail時，不要去刪instructor
     */
    @Test
    @Transactional
    void deleteInstructorDetail(){
        int instructorDetailId = 1;
        appDAO.deleteInstructorDetailById(instructorDetailId);

        InstructorDetail instructorDetail = appDAO.findInstructorDetailById(instructorDetailId);

        int instructorId = 4;
        Instructor instructor = appDAO.findInstructorById(instructorId);

        // 刪了instructorDetail 不會去Cascade delete instructor
        assertNull(instructorDetail);
        assertNotNull(instructor);
    }

    // one to many: one instructor has many courses
    @Test
    @Transactional
    void createInstructorWithCourses(){
        Instructor instructor = new Instructor("Susan", "Agave", "susan.agave@gmail.com");
        InstructorDetail instructorDetail = new InstructorDetail("http://www.youtube.com", "Video Games");
        instructor.setInstructorDetail(instructorDetail);

        // add courses to the instructor
        Course course = new Course("Air Jordan - how to tilt and shoot straight");
        Course course2 = new Course("The Germany Masterclass");
        instructor.add(course);
        instructor.add(course2);

        // save the instructor and its courses and its detail
        appDAO.save(instructor);

        Instructor susan = instructorRepository.findByEmail("susan.agave@gmail.com");
        int susanId = susan.getId();
        List<Course> courses = courseRepository.findByInstructorId(susanId);
        assertEquals(2,courses.size());
    }

    @Test
    void findInstructorWithCourses(){
        int instructorId = 7;
        Instructor instructor = appDAO.findInstructorById(instructorId);
        assertNotNull(instructor);
    }

    @Test
    void findInstructorWithCoursesByJoinFetch(){
        int instructorId = 7;
        Instructor instructor = appDAO.findInstructorByIdJoinFetch(instructorId);
        List<Course> courses = instructor.getCourses();
        assertNotNull(instructor);
        assertNotNull(courses);
        assertEquals(2,courses.size());
    }

    @Test
    @Transactional
    void updateInstructor(){
        int id = 4;
        Instructor instructor = appDAO.findInstructorById(id);
        instructor.setLastName("RE2");
        appDAO.update(instructor);

        instructor = appDAO.findInstructorById(id);
        assertEquals("RE2",instructor.getLastName());
    }

    @Test
    @Transactional
    void updateCourse(){
        int courseId = 5;
        Course course = appDAO.findCourseById(courseId);
        course.setTitle("RE4");
        appDAO.update(course);

        course = appDAO.findCourseById(courseId);
        assertEquals("RE4",course.getTitle());
    }

    @Test
    @Transactional
    void deleteCourseById(){
        int courseId = 5;
        Course course = appDAO.findCourseById(courseId);
        assertNotNull(course); // existed
        appDAO.deleteCourseById(courseId);
        course = appDAO.findCourseById(courseId);
        assertNull(course); // gone
    }

    // one to many: one course has many reviews
    @Test
    @Transactional
    void createCourseAndReviews(){
        Course course = new Course("how to learn English");
        course.addReview(new Review("good teacher"));
        course.addReview(new Review("well done"));
        course.addReview(new Review("teacher is single"));
        appDAO.save(course);
    }

    @Test
    void retrieveCourseAndReviewsJoinFetch(){
        int courseId = 7;
        Course course = appDAO.findCourseAndReviewsByCourseId(courseId);
        List<Review> reviews = course.getReviews();
        assertNotNull(course);
        assertNotNull(reviews);
    }

    // many to many: obviously
    @Test
    @Transactional
    void createCourseAndStudents(){
        Course course = new Course("how to keep a good habit");
        Student student = new Student("john", "doe@gmail.com", 33);
        Student student2 = new Student("Mary", "public@gmail.com", 44);
        course.addStudent(student);
        course.addStudent(student2);
        appDAO.save(course);

        Course courseByTitle = courseRepository.findByTitle("how to keep a good habit");
        Course courseWithStudents = appDAO.findCourseAndStudentsByCourseId(courseByTitle.getId());
        assertNotNull(courseWithStudents);
        assertNotNull(courseWithStudents.getStudents());
    }

    @Test
    void retrieveCourseAndStudentsJoinFetch(){
        int courseId = 1;
        Course courseWithStudents = appDAO.findCourseAndStudentsByCourseId(courseId);
        assertNotNull(courseWithStudents);
        assertNotNull(courseWithStudents.getStudents());
    }

    @Test
    @Transactional
    void createStudentAndCourses(){
        Student student = new Student("Ashley", "ashley@gmail.com", 20);
        Course course = new Course("RE2 guide");
        Course course2 = new Course("RE4 guide");
        student.addCourse(course);
        student.addCourse(course2);
        appDAO.save(student);

        Student studentByEmail = studentRepository.findByEmail("ashley@gmail.com");
        int studentId = studentByEmail.getId();
        Student studentAndCoursesByStudentId = appDAO.findStudentAndCoursesByStudentId(studentId);
        List<Course> courses = studentAndCoursesByStudentId.getCourses();
        assertNotNull(studentAndCoursesByStudentId);
        assertNotNull(courses);
    }

    @Test
    void retrieveStudentAndCoursesJoinFetch(){
        int studentId = 1;
        Student student = appDAO.findStudentAndCoursesByStudentId(studentId);
        List<Course> courses = student.getCourses();
        assertEquals(3,courses.size());
    }

    @Test
    @Transactional
    void addMoreCoursesForStudent(){
        int studentId = 1;
        Student student = appDAO.findStudentAndCoursesByStudentId(studentId);
        student.addCourse(new Course("Attack on Titan"));
        student.addCourse(new Course("To you 2000 years from now"));
        appDAO.update(student);
        student = appDAO.findStudentAndCoursesByStudentId(studentId);
        List<Course> courses = student.getCourses();
        assertEquals(5, courses.size());
    }

    @Test
    @Transactional
    void deleteStudentById(){
        int studentId = 1;

        Student student = appDAO.findStudentAndCoursesByStudentId(studentId);
        List<Course> courses = student.getCourses();
        assertEquals(3,courses.size());

        appDAO.deleteStudentById(studentId); // delete the student

        // the student's 3 courses still exist
        for (Course course: courses){
            Course courseById = appDAO.findCourseById(course.getId());
            assertNotNull(courseById);
        }
    }

}





















