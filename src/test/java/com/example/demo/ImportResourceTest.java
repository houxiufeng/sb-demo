package com.example.demo;

import com.example.demo.entity.Family;
import com.example.demo.entity.Home;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ImportResourceTest {

    @Autowired
    private ConfigurableApplicationContext context;

    @Test
    public void testImportResource() {
        System.out.println(context.containsBean("testBeanService"));
    }
}
