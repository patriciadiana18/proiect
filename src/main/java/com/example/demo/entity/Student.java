package com.example.demo.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;

@Entity
public class Student {

    private @Id @GeneratedValue Long id;
    private String firstName;
    private String lastName;
    private int age;
    private boolean scholarship;
    public enum grades{
        BAD,
        MEDIUM,
        GOOD
    }
    private grades grades;
    public Student() {}

    public Student(String firstName, String lastName, int age, boolean scholarship, grades grades) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.scholarship = scholarship;
        this.grades = grades;
    }

    public Student.grades getGrades() {
        return grades;
    }

    public int getAge() {
        return age;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean isScholarship() {
        return scholarship;
    }

    public void setScholarship(boolean scholarship) {
        this.scholarship = scholarship;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setGrades(Student.grades grades) {
        this.grades = grades;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}