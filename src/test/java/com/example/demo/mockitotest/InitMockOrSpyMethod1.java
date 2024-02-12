package com.example.demo.mockitotest;

import com.example.demo.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * 参考视频:
 * https://www.bilibili.com/video/BV1P14y1k7Hi/?p=17&spm_id_from=pageDriver&vd_source=a829e69232988cd1691360547865a27a
 * 初始化mock/spy有三种方式，第一种
 */
@ExtendWith(MockitoExtension.class)
public class InitMockOrSpyMethod1 {

    @Mock
    private ArticleService mockArticleService;
    @Spy
    private ArticleService spyArticleService;

    @Test
    public void test1() {
        System.out.println(Mockito.mockingDetails(mockArticleService).isMock());
        System.out.println(Mockito.mockingDetails(mockArticleService).isSpy());
        System.out.println(Mockito.mockingDetails(spyArticleService).isMock());
        //Spy对象是一种特殊的mock对象
        System.out.println(Mockito.mockingDetails(spyArticleService).isSpy());
    }

}
