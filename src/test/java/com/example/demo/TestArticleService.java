package com.example.demo;

import com.example.demo.dao.ArticleJdbcDao;
import com.example.demo.entity.Article;
import com.example.demo.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestArticleService {

    @Autowired
    private ArticleService articleService;

    @Test
    public void testTransaction() {
//        articleService.testTransactional1();
//        articleService.testTransactional2();
//        articleService.testTransactional3();
//        articleService.testTransactional4();
//        articleService.testTransactional5();
        articleService.testTransactional6();
    }
    @Test
    public void testFindById() {
        Article article = articleService.getById(1L);
        System.out.println(article);
    }
}
