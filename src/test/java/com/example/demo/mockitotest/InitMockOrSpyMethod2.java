package com.example.demo.mockitotest;

import com.example.demo.service.ArticleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * 初始化mock/spy有三种方式，第二种
 */
public class InitMockOrSpyMethod2 {

    private ArticleService mockArticleService;
    private ArticleService spyArticleService;

    @BeforeEach
    public void init() {
        mockArticleService = Mockito.mock(ArticleService.class);
        spyArticleService = Mockito.spy(ArticleService.class);
    }

    @Test
    public void test1() {
        System.out.println(Mockito.mockingDetails(mockArticleService).isMock());
        System.out.println(Mockito.mockingDetails(mockArticleService).isSpy());
        System.out.println(Mockito.mockingDetails(spyArticleService).isMock());
        //Spy对象是一种特殊的mock对象
        System.out.println(Mockito.mockingDetails(spyArticleService).isSpy());
    }

}
