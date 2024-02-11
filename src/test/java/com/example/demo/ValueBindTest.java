package com.example.demo;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Family;
import com.example.demo.entity.Home;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ValueBindTest {

    @Value("${author}")
    private String author;

    @Value("${my.first-name}")
    private String myFirstName;

    @Autowired
    private Family family;
    @Autowired
    private Home home;
    @Autowired
    private Employee employee;

    @Test
    public void testBind() {
        System.out.println(author);
        System.out.println(myFirstName);
        System.out.println(family);
        System.out.println(home);
        System.out.println(employee);
    }
}
