package com.example;

import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Student s = new Student();
        s.setId(1);
        s.setName("Jane Doe");
        s.setAge(20);
        s.setSubjects(Arrays.asList("Mathematics", "English", "History"));

        System.out.println("ID: " + s.getId());
        System.out.println("Name: " + s.getName());
        System.out.println("Age: " + s.getAge());
        System.out.println("Subjects: " + s.getSubjects());
    }
}