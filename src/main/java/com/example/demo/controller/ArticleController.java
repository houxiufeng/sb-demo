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
@RequestMapping("/rest")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    //获取一篇Article，使用GET方法,根据id查询一篇文章
    //@RequestMapping(value = "/articles/{id}",method = RequestMethod.GET)
    @GetMapping("/articles/{id}")
    public Article getArticle(@PathVariable("id") Long id){

        //使用lombok提供的builder构建对象
        Article article = Article.builder()
                .id(id)
                .author("zimug")
                .content("spring boot 从青铜到王者")
                .createTime(new Date())
                .title("t1").build();

        log.info("article:" + article);

        return article;
    }


    //增加一篇Article ，使用POST方法(RequestBody方式接收参数)
    //@RequestMapping(value = "/articles",method = RequestMethod.POST)
    @PostMapping("/articles")
    public AjaxResponse saveArticle(@RequestBody Article article,
                                    @RequestHeader String aaa){

        //因为使用了lombok的Slf4j注解，这里可以直接使用log变量打印日志
        log.info("saveArticle:" + article);
        return AjaxResponse.success(article);
    }

    //增加一篇Article ，使用POST方法(RequestParam方式接收参数)
  /*@PostMapping("/articles")
  public AjaxResponse saveArticle(@RequestParam  String author,
                                  @RequestParam  String title,
                                  @RequestParam  String content,
                                  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                  @RequestParam  Date createTime){

    log.info("saveArticle:" + createTime);
    return AjaxResponse.success();
  }*/


    //更新一篇Article，使用PUT方法，以id为主键进行更新
    //@RequestMapping(value = "/articles",method = RequestMethod.PUT)
    @PutMapping("/articles")
    public AjaxResponse updateArticle(@RequestBody Article article){
        if(article.getId() == null){
            //article.id是必传参数，因为通常根据id去修改数据
            //TODO 抛出一个自定义的异常
            throw new RuntimeException("id is null");
        }

        log.info("updateArticle:" + article);
        return AjaxResponse.success();
    }

    //删除一篇Article，使用DELETE方法，参数是id
    //@RequestMapping(value = "/articles/{id}",method = RequestMethod.DELETE)
    @DeleteMapping("/articles/{id}")
    public AjaxResponse deleteArticle(@PathVariable("id") Long id){

        log.info("deleteArticle:" + id);
        return AjaxResponse.success();
    }

}
