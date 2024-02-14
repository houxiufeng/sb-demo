package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.demo.entity.Article;
import com.example.demo.mapper.ArticleMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * mybatis plus demo
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestMybatisPlus {

    @Autowired
    private ArticleMapper articleMapper;

    /**
     * save操作：article并不完全需要跟数据库对应，reader字段就是一个证据
     * 但是select 操作不对应会有问题，用 @TableField(exist=false)
     */
    @Test
    public void testSave() {
        Article article = Article.builder()
                .author("zimug")
                .content("spring boot 从青铜到王者")
                .createTime(new Date())
                .title("springboot study")
                .build();
        articleMapper.insert(article);
    }

    /**
     * 没有写的属性不做任何处理.
     */
    @Test
    public void testUpdate() {
        Article article = Article.builder()
                .id(2L)
                .author("fitzxxx")
//                .content("spring boot 从青铜到王者")
//                .createTime(new Date())
//                .title("springboot study")
                .build();
        articleMapper.updateById(article);
    }

    /**
     * 不存在的id随便执行多少次无所谓，就是幂等性。
     */
    @Test
    public void testDelete() {
        articleMapper.deleteById(2L);
    }

    /**
     * 记录不存在会返回null
     * 如果有数据库中不存在的字段需要用 @TableField(exist=false) 排除下。
     */
    @Test
    public void testFindById() {
        Article article = articleMapper.selectById(1L);
        System.out.println(article);
    }
    @Test
    public void testFindAll() {
        List<Article> articles = articleMapper.selectList(null);
        articles.forEach(System.out::println);
    }

    @Test
    public void testFindByConditions() throws ParseException {
        //QueryWrapper 方式
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("author", "zimug");
        queryWrapper.eq("create_time", "2024-02-14 17:16:38");//create_time 必须跟数据库里面的字段一样
        List<Article> articles = articleMapper.selectList(queryWrapper);
        articles.forEach(System.out::println);
        System.out.println("=================================");

        //entity 方式
        Article article = new Article();
        article.setAuthor("zimug");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
        Date date = formatter.parse("2024-02-14 20:52:07");
        article.setCreateTime(date);
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>(article);
        List<Article> articles2 = articleMapper.selectList(articleQueryWrapper);
        articles2.forEach(System.out::println);
        System.out.println("=================================");

        /**
         * lambda 条件构造器好处是由于它是通过调用实体类中的方法，如果方法名称写错，会直接报错，从而提前纠错。
         * 不像 QueryWrapper 是通过自己写表中相应的属性来构造 where 条件，容易发生拼写错误，等到运行时才发现。
         */
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Article::getAuthor, "zimug").eq(Article::getId, 3L);
        List<Article> articles3 = articleMapper.selectList(lambdaQueryWrapper);
        articles3.forEach(System.out::println);


    }
}
