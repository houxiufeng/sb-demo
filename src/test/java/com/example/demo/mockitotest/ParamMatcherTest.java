package com.example.demo.mockitotest;

import com.example.demo.entity.Article;
import com.example.demo.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * 初始化mock/spy有三种方式，第一种
 */
@ExtendWith(MockitoExtension.class)
public class ParamMatcherTest {

    @Mock
    private ArticleService mockArticleService;

    /**
     * mock对象不会调用真实方法，直接返回mock对象默认值
     * int 0, object null, collection []
     */
    @Test
    public void test1() {
        Article article = mockArticleService.getById(1L);
        //null
        System.out.println(article);

        int i = mockArticleService.deleteArticle(1L);
        //0
        System.out.println(i);
    }

    /**
     * 只拦截特定参数值
     */
    @Test
    public void test2() {
        //先设计剧本,当调用mockArticleService 并且参数是 1L 的时候，结果返回10, 注意参数只能是1的时候才返回10，其他仍然返回默认值。
        Mockito.doReturn(10).when(mockArticleService).deleteArticle(1L);
        int i = mockArticleService.deleteArticle(1L);
        //10
        System.out.println(i);
        int k = mockArticleService.deleteArticle(2L);
        //0
        System.out.println(k);
    }

    /**
     * 拦截任意参数
     */
    @Test
    public void test3() {
        //先设计剧本,当调用mockArticleService 并且参数是 任意Long类型的 的时候，结果返回10。
        Mockito.doReturn(10).when(mockArticleService).deleteArticle(ArgumentMatchers.anyLong());
        int i = mockArticleService.deleteArticle(1L);
        //10
        System.out.println(i);
        int k = mockArticleService.deleteArticle(2L);
        //0
        System.out.println(k);
    }

    /**
     * 校验某个方法调用了一次
     * 注意：如果使用ArgumentMatchers 作为参数，那么要么都用matcher, 要么都别用，不要混合用，会报错。
     */
    @Test
    public void test4() {
        //先设计剧本,当调用mockArticleService 并且参数是 任意Long类型的 的时候，结果返回10。
        Mockito.doReturn(10).when(mockArticleService).deleteArticle(ArgumentMatchers.anyLong());
        int i = mockArticleService.deleteArticle(1L);
        //10
        System.out.println(i);
        //写2会报错，因为只执行了一次。一般用来验证某个方法是否执行过.
        Mockito.verify(mockArticleService, Mockito.times(1)).deleteArticle(ArgumentMatchers.anyLong());

    }

}
