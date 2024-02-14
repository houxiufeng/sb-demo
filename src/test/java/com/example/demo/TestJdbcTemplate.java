package com.example.demo;

import com.example.demo.dao.ArticleJdbcDao;
import com.example.demo.entity.Article;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestJdbcTemplate {

    @Autowired
    private ArticleJdbcDao articleJdbcDao;

    @Test
    public void testSave() {
        Article article = Article.builder()
                .author("zimug")
                .content("spring boot 从青铜到王者")
                .createTime(new Date())
                .title("springboot study")
                .build();
        articleJdbcDao.saveArticle(article);
    }
    @Test
    public void testUpdate() {
        Article article = Article.builder()
                .id(2L)
                .author("fitz")
                .content("spring boot 从青铜到王者")
                .createTime(new Date())
                .title("springboot study")
                .build();
        articleJdbcDao.updateById(article);
    }
    @Test
    public void testDelete() {
        articleJdbcDao.deleteArticle(1L);
    }
    @Test
    public void testFindById() {
        Article article = articleJdbcDao.findById(2L);
        System.out.println(article);
    }
    @Test
    public void testFindAll() {
        List<Article> articles = articleJdbcDao.findAll();
        articles.forEach(System.out::println);
    }
}
