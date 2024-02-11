package com.example.demo.controller;

import com.example.demo.entity.Article;
import com.example.demo.service.MockService;
import com.example.demo.service.ArticleService;
import com.example.demo.vos.AjaxResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/mock")
public class MockController {

    @Autowired
    private MockService mockService;

    @PostMapping("/articles")
    public AjaxResponse saveSomething(@RequestBody Article article){

        log.info("saveArticle:" + article);
        String s = mockService.saveArticle(article);
        log.info("saveArticle result:" + s);
        return AjaxResponse.success(article);
    }

}
