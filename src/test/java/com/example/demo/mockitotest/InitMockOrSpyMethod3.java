package com.example.demo.mockitotest;

import com.example.demo.service.ArticleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

/**
 * 初始化mock/spy有三种方式，第三种
 */
public class InitMockOrSpyMethod3 {

    @Mock
    private ArticleService mockArticleService;
    @Spy
    private ArticleService spyArticleService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
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
