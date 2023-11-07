package com.example.hw.annotationConfiguration;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("com/example/hw/.annotationConfiguration");

        AnnotationStudent student = context.getBean(AnnotationStudent.class);

        System.out.println(student);


    }
}
