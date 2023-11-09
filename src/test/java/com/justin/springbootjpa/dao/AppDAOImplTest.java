package com.justin.springbootjpa.dao;

import com.justin.springbootjpa.entity.Instructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AppDAOImplTest {

    @Autowired
    private AppDAO appDAO;

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

    }


}