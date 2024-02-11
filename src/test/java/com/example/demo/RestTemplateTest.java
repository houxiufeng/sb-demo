package com.example.demo;

import com.example.demo.common.JacksonUtil;
import com.example.demo.common.RestTemplateUtil;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Family;
import com.example.demo.entity.Home;
import com.example.demo.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class RestTemplateTest {

//    private static final String BASE_URL = "http://192.168.123.184:8888";
    private static final String BASE_URL = "http://www.220910.xyz:8888";
//    private static final String BASE_URL = "http://localhost:8888";

    @Test
    public void get(){
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("id","2");
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("token", "tk");
        headerMap.put("author", "fitz");
        String resultStr = RestTemplateUtil.get(BASE_URL + "/api/user",null,paramMap);
        System.out.println(resultStr);
    }

    @Test
    public void postForm(){
        MultiValueMap<String,Object> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("name","fitz");
        paramMap.add("age", 20);
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("token", "tk");
        headerMap.put("author", "fitz");
        String resultStr = RestTemplateUtil.postForm(BASE_URL + "/api/form-test",headerMap,paramMap);
        System.out.println(resultStr);
    }

    @Test
    public void postJson(){
        User user = new User();
        user.setId(1L);
        user.setName("xxxx");
        user.setAge(23);

        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("token", "tk");
        headerMap.put("author", "fitz");
        String resultStr = RestTemplateUtil.postJson(BASE_URL + "/api/user",headerMap, JacksonUtil.bean2Json(user));
        System.out.println(resultStr);
    }

    @Test
    public void putJson(){
        User user = new User();
        user.setId(1L);
        user.setName("xxxx");
        user.setAge(23);

        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("token", "tk");
        headerMap.put("author", "fitz");
        String resultStr = RestTemplateUtil.putJson(BASE_URL + "/api/user",headerMap, JacksonUtil.bean2Json(user));
        System.out.println(resultStr);
    }

    @Test
    public void delete(){
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("id","2");
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("token", "tk");
        headerMap.put("author", "fitz");
        String resultStr = RestTemplateUtil.delete(BASE_URL + "/api/user",headerMap,paramMap);
        System.out.println(resultStr);
    }

}
