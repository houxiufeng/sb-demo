package com.example.demo.service;

import com.example.demo.dao.ArticleJdbcDao;
import com.example.demo.entity.Article;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * squaretest插件自动生成。生成了大体的框架，大概率有错误，不过已经可以节省很多时间了。
 * 1. 先运行一下，根据错误进行修改。
 * 2. 被测试的类要加上 @Spy(因为可能需要对某些方法进行插桩)
 * 3. 依次检查各个方法，根据错误提示修改。
 */
//@ExtendWith(MockitoExtension.class)
//class ArticleServiceTest {

//    @Mock
//    private ArticleJdbcDao mockArticleJdbcDao;
//
//    @InjectMocks
//    @Spy
//    private ArticleService articleServiceUnderTest;
//
//    @Test
//    void testTestTransactional1() {
//        // Setup
//        // Configure ArticleJdbcDao.findById(...).
//        final Article article = Article.builder()
//                .author("author")
//                .title("title")
//                .content("content")
//                .createTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
//                .build();
//        when(mockArticleJdbcDao.findById(2L)).thenReturn(article);
//
//        when(mockArticleJdbcDao.updateById(Article.builder()
//                .author("author")
//                .title("title")
//                .content("content")
//                .createTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
//                .build())).thenReturn(0);
//
//        // Run the test
//        articleServiceUnderTest.testTransactional1();
//
//        // Verify the results
//        verify(mockArticleJdbcDao).saveArticle(Article.builder()
//                .author("author")
//                .title("title")
//                .content("content")
//                .createTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
//                .build());
//    }
//
//    @Test
//    void testTestTransactional2() {
//        // Setup
//        // Configure ArticleJdbcDao.findById(...).
//        final Article article = Article.builder()
//                .author("author")
//                .title("title")
//                .content("content")
//                .createTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
//                .build();
//        when(mockArticleJdbcDao.findById(2L)).thenReturn(article);
//
//        when(mockArticleJdbcDao.updateById(Article.builder()
//                .author("author")
//                .title("title")
//                .content("content")
//                .createTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
//                .build())).thenReturn(0);
//
//        // Run the test
//        articleServiceUnderTest.testTransactional2();
//
//        // Verify the results
//        verify(mockArticleJdbcDao).saveArticle(Article.builder()
//                .author("author")
//                .title("title")
//                .content("content")
//                .createTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
//                .build());
//    }
//
//    @Test
//    void testTestTransactional3() {
//        // Setup
//        // Run the test
//        articleServiceUnderTest.testTransactional3();
//
//        // Verify the results
//        verify(mockArticleJdbcDao).saveArticle(Article.builder()
//                .author("author")
//                .title("title")
//                .content("content")
//                .createTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
//                .build());
//    }
//
//    @Test
//    void testTestTransactional4() {
//        // Setup
//        // Run the test
//        articleServiceUnderTest.testTransactional4();
//
//        // Verify the results
//        verify(mockArticleJdbcDao).saveArticle(Article.builder()
//                .author("author")
//                .title("title")
//                .content("content")
//                .createTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
//                .build());
//    }
//
//    @Test
//    void testTestTransactional5() {
//        // Setup
//        // Run the test
//        articleServiceUnderTest.testTransactional5();
//
//        // Verify the results
//        verify(mockArticleJdbcDao).saveArticle(Article.builder()
//                .author("author")
//                .title("title")
//                .content("content")
//                .createTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
//                .build());
//    }
//
//    @Test
//    void testTestTransactional6() {
//        // Setup
//        // Run the test
//        articleServiceUnderTest.testTransactional6();
//
//        // Verify the results
//        verify(mockArticleJdbcDao).saveArticle(Article.builder()
//                .author("author")
//                .title("title")
//                .content("content")
//                .createTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
//                .build());
//    }
//
//    @Test
//    void testSaveArticle() {
//        // Setup
//        final Article article = Article.builder()
//                .author("author")
//                .title("title")
//                .content("content")
//                .createTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
//                .build();
//
//        // Run the test
//        articleServiceUnderTest.saveArticle(article);
//
//        // Verify the results
//        verify(mockArticleJdbcDao).saveArticle(Article.builder()
//                .author("author")
//                .title("title")
//                .content("content")
//                .createTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
//                .build());
//    }
//
//    @Test
//    void testSaveArticleWithError() {
//        // Setup
//        final Article article = Article.builder()
//                .author("author")
//                .title("title")
//                .content("content")
//                .createTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
//                .build();
//
//        // Run the test
//        articleServiceUnderTest.saveArticleWithError(article);
//
//        // Verify the results
//        verify(mockArticleJdbcDao).saveArticle(Article.builder()
//                .author("author")
//                .title("title")
//                .content("content")
//                .createTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
//                .build());
//    }
//
//    @Test
//    void testDeleteArticle() {
//        // Setup
//        when(mockArticleJdbcDao.deleteArticle(0L)).thenReturn(0);
//
//        // Run the test
//        final int result = articleServiceUnderTest.deleteArticle(0L);
//
//        // Verify the results
//        assertThat(result).isEqualTo(0);
//    }
//
//    @Test
//    void testGetById() {
//        // Setup
//        final Article expectedResult = Article.builder()
//                .author("author")
//                .title("title")
//                .content("content")
//                .createTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
//                .build();
//
//        // Configure ArticleJdbcDao.findById(...).
//        final Article article = Article.builder()
//                .author("author")
//                .title("title")
//                .content("content")
//                .createTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
//                .build();
//        when(mockArticleJdbcDao.findById(0L)).thenReturn(article);
//
//        // Run the test
//        final Article result = articleServiceUnderTest.getById(0L);
//
//        // Verify the results
//        assertThat(result).isEqualTo(expectedResult);
//    }
//
//    @Test
//    void testGetNumber() {
//        assertThat(articleServiceUnderTest.getNumber(0)).isEqualTo(0);
//    }
//
//    @Test
//    void testUpdateById() {
//        // Setup
//        final Article article = Article.builder()
//                .author("author")
//                .title("title")
//                .content("content")
//                .createTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
//                .build();
//        when(mockArticleJdbcDao.updateById(Article.builder()
//                .author("author")
//                .title("title")
//                .content("content")
//                .createTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
//                .build())).thenReturn(0);
//
//        // Run the test
//        final int result = articleServiceUnderTest.updateById(article);
//
//        // Verify the results
//        assertThat(result).isEqualTo(0);
//    }
//
//    @Test
//    void testUpdateById2() {
//        // Setup
//        final Article article = Article.builder()
//                .author("author")
//                .title("title")
//                .content("content")
//                .createTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
//                .build();
//        when(mockArticleJdbcDao.updateById(Article.builder()
//                .author("author")
//                .title("title")
//                .content("content")
//                .createTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
//                .build())).thenReturn(0);
//
//        // Run the test
//        final int result = articleServiceUnderTest.updateById2(article);
//
//        // Verify the results
//        assertThat(result).isEqualTo(0);
//    }
//}
