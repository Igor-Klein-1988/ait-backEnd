package com.example.hw.xmlConfiguration;

import com.example.hw.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainXml {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-factory-demo.xml");
        Student student = (Student) context.getBean("student");

        System.out.println(student);

    }
}
