package com.example.demo.service;

import com.example.demo.entity.Article;
import org.springframework.stereotype.Service;

@Service
public class MockService {
    public String saveArticle(Article article) {
        return "hhh";
    }
}
