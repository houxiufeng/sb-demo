package com.example.demo.entity;

import com.example.demo.common.JacksonUtil;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    private Long  id;
    private String author;
    private String title;
    private String content;
    private Date createTime;

    private List<Reader> reader;

    public static void main(String[] args) {
        Article article = Article.builder()
                .id(1L)
                .author("zimug")
                .content("spring boot 从青铜到王者")
                .createTime(new Date())
                .title("springboot study")
                .reader(Lists.newArrayList(new Reader("allen", 12), new Reader("fitz", 23)))
                .build();
        String s = JacksonUtil.bean2Json(article);
        System.out.println(s);
    }
}