package com.justin.springbootjpa.utils;


import com.github.javafaker.Faker;
import com.justin.springbootjpa.entity.Student;


import java.util.ArrayList;
import java.util.List;

/*
    public final class ExampleUtilities {
        public static final String NAME = "utils' name";
        public static int add(int i, int j) {}
    }
 */
public final class StudentUtils {

    public static Student createOneStudent() {
        Faker faker = new Faker();
        String name = faker.name().name();
        String email = String.format("%s@gmail.com", name);
        Integer age = faker.number().numberBetween(18, 40);
        Student student = new Student(name, email, age);
        return student;
    }

    public static Student createOneStudent(String name) {
        Faker faker = new Faker();
        String email = String.format("%s@gmail.com", name);
        Integer age = faker.number().numberBetween(18, 40);
        Student student = new Student(name, email, age);
        return student;
    }

    public static Student createOneStudent(String name, Integer age) {
        Faker faker = new Faker();
        String email = String.format("%s@gmail.com", name);
        Student student = new Student(name, email, age);
        return student;
    }

    public static Student createOneStudent(String name, Integer age, String email) {
        Student student = new Student(name, email, age);
        return student;
    }

    public static ArrayList<Student> createStudentList(int quantity) {
        ArrayList<Student> students = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 0; i < quantity; i++) {
            String name = faker.name().name();
            String email = String.format("%s@gmail.com", name);
            Integer age = faker.number().numberBetween(18,40);
            Student student = new Student(name, email, age);
            students.add(student);
        }
        return students;
    }

//    public static List<PlainStudent> createPlainStudents(int quantity) {
//        List<PlainStudent> students = new ArrayList<>();
//        Faker faker = new Faker();
//        for (int i = 0; i < quantity; i++) {
//            String name = faker.name().name();
//            String email = String.format("%s@gmail.com", name);
//            Integer age = faker.number().numberBetween(18,40);
//            PlainStudent student = new PlainStudent(name, email, age);
//            students.add(student);
//        }
//        return students;
//    }

}

