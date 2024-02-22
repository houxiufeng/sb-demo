package com.example.demo.controller;

import com.example.demo.entity.Home;
import com.example.demo.event.MyEvent;
import com.example.demo.exception.CustomException;
import com.example.demo.exception.ResponseCodeEnum;
import com.example.demo.vos.AjaxResponse;
import com.home.demo.service.AnimalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private AnimalService animalService;

    /**
     * This method is a sample controller that returns a greeting message.
     *
     * @return a greeting message
     */
    @RequestMapping("hi")
    public String sayHi() {
        return "hi sb-demo";
    }

    @RequestMapping("hello")
    public AjaxResponse sayHello() {
        log.info("test sayHello");
        applicationContext.publishEvent(new MyEvent("hello"));
        return AjaxResponse.success("bingo");
    }

    @RequestMapping("error1")
    public AjaxResponse error1() {
        log.info("test error");
        int a = 10/0;
        return AjaxResponse.success("bingo");
    }

    @RequestMapping("error2")
    public AjaxResponse error2() {
        log.info("test error2");
        try {
            int a = 10/0;
        } catch (Exception e) {
            throw new CustomException(ResponseCodeEnum.SYSTEM_ERROR, "wtf, error!");
        }
        return AjaxResponse.success("bingo");
    }

    /**
     * 测试earlybird,之所以封装成一个对象，是为了防止GlobalResponseAdvice 里的类型转化错误。
     * ref: https://cloud.tencent.com/developer/article/1818338
     *
     * @return
     */
    @GetMapping("/earlybird")
    public Home earlybird() {
        Home home = new Home();
        home.setName(animalService.say());
        return home;
    }
}
