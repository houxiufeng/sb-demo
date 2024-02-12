package com.example.demo.mockitotest;

import com.example.demo.dao.ArticleJdbcDao;
import com.example.demo.entity.Article;
import com.example.demo.entity.Reader;
import com.example.demo.service.ArticleService;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InjectMockTest {
    /**
     * @InjectMocks作用：被测试的类一般用这个注解，另外再加一个@Spy
     * 若@InjectMocks声明的变量需要用到mock/spy对象，mockito会自动把当前类里面的mock或者spy成员按类型或者名字进行注入到 mockArticleService 里面对应的属性上。
     * 原理：构造器注入，setter注入，字段反射注入
     * 被@InjectMocks 标注的不能是接口，因为mockito会创建对应的对象实例
     * 默认创建的对象就是未经过mockito处理的普通的对象，因此长配合@Spy注解使其成为调用真实方法的mock对象
     */
    @InjectMocks
    @Spy
    private ArticleService mockArticleService;

    //加入@Mock或者@Spy注释后，ArticleService 里面的 ArticleJdbcDao 会自动被实例化为一个mock/spy对象。
    @Mock
    private ArticleJdbcDao articleJdbcDao;




    /**
     *
     */
    @Test
    public void test1() {
        Article article = Article.builder()
                .id(1L)
                .author("zimug")
                .content("spring boot 从青铜到王者")
                .createTime(new Date())
                .title("springboot study")
                .reader(Lists.newArrayList(new Reader("allen", 12), new Reader("fitz", 23)))
                .build();
        when(articleJdbcDao.updateById(any())).thenReturn(3);
        //原始的方法会被执行，并且articleJdbcDao.updateById 按照打桩的预想来输出。
        int i = mockArticleService.updateById2(article);
        Assertions.assertEquals(3, i);
    }

}
