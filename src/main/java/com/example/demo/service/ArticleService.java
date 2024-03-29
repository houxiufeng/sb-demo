package com.example.demo.service;

import com.example.demo.annotation.Mylog;
import com.example.demo.dao.ArticleJdbcDao;
import com.example.demo.entity.Article;
import com.example.demo.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.Date;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleJdbcDao articleJdbcDao;
    @Autowired
    private ArticleMapper articleMapper;

    //https://juejin.cn/post/6844904096747503629

    //save 操作会回滚
    @Transactional
    public void testTransactional1() {
        Article article1 = buildArticle("allen");
        saveArticle(article1);
        if (2 > 1) {
            throw new RuntimeException("test exception");
        }
        Article article = articleJdbcDao.findById(2);
        article.setAuthor("xxxxxx");
        updateById(article);
    }

    @Transactional
    //save,update 操作都会回滚
    public void testTransactional2() {
        Article article1 = buildArticle("allen");
        saveArticle(article1);
        Article article = articleJdbcDao.findById(2);
        article.setAuthor("xxxxxx");
        updateById(article);
        System.out.println("over");
    }

    //save操作回滚失败
    public void testTransactional3() {
        Article article1 = buildArticle("allen");
        saveArticleWithError(article1);
    }

    @Transactional
    //save 操作会回滚
    public void testTransactional4() {
        Article article1 = buildArticle("allen");
        saveArticleWithError(article1);
    }

    //save 操作回滚失败
    public void testTransactional5() {
        Article article1 = buildArticle("allen");
        saveArticle(article1);
        if (2 > 1) {
            throw new RuntimeException("test exception");
        }
    }

    //save 操作回滚失败
    @Transactional
    public void testTransactional6() {
        Article article1 = buildArticle("allen");
        try {
            saveArticleWithError(article1);
        } catch (RuntimeException e) {
            System.out.println("error");
        }

    }

    @Transactional
    public void saveArticle(Article article) {
        articleJdbcDao.saveArticle(article);
    }

    @Transactional
    public void saveArticleWithError(Article article) {
        articleJdbcDao.saveArticle(article);
        if (2 > 1) {
            throw new RuntimeException("test exception");
        }
    }

    public int deleteArticle(long id) {
        return articleJdbcDao.deleteArticle(id);
    }

    @Mylog(level = 2, value = "test annotation in getById")
    public Article getById(long id) {
//        int a = 10/0;
//        return articleJdbcDao.findById(id);
        return articleMapper.selectById(id);
    }

    /**
     * only for mock test
     * @return
     */
    public int getNumber(int n) {
        System.out.println("cal getNumber");
        return n + 1;
    }

    @Transactional
    public int updateById(Article article) {
        int n = articleJdbcDao.updateById(article);
        if (2 > 1) {
            throw new RuntimeException("test exception");
        }
        return n;
    }

    public int updateById2(Article article) {
        int n = articleJdbcDao.updateById(article);
        System.out.println("updateById2");
        return n;
    }

    private Article buildArticle(String author) {
        Article article = Article.builder()
                .author(author)
                .content("spring boot 从青铜到王者")
                .createTime(new Date())
                .title("springboot study")
                .build();
        return article;
    }
}
