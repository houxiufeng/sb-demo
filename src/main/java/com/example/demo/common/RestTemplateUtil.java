package com.example.demo.common;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class RestTemplateUtil {

    private static final String EMPTY_STR = "";
    private static RestTemplate restTemplate = new RestTemplate();

    /**
     * get请求
     * @param url
     * @param headerMap
     * @param paramMap
     * @return
     */
    public static String get(String url, Map<String,String> headerMap, Map<String,String> paramMap){
        HttpHeaders headers = new HttpHeaders();
        if(!CollectionUtils.isEmpty(headerMap)){
            headerMap.forEach((k,v)-> headers.set(k,v));
        }
        StringBuffer paramStr = new StringBuffer(EMPTY_STR);
        if(!CollectionUtils.isEmpty(paramMap)){
            paramMap.forEach((k,v)->{
                if(paramStr.toString().equals(EMPTY_STR)){
                    paramStr.append("?").append(k).append("=").append(v);
                }else{
                    paramStr.append("&").append(k).append("=").append(v);
                }
            });
        }
        HttpEntity<String> httpEntity = restTemplate.exchange(url+paramStr.toString(), HttpMethod.GET,CollectionUtils.isEmpty(headerMap) ? null : new HttpEntity<>(headers),String.class);
        return httpEntity.getBody();
    }

    /**
     * post JSON
     * @param url
     * @param headerMap
     * @param paramObjectStr
     * @return
     */
    public static String postJson(String url, Map<String,String> headerMap, String paramObjectStr){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        if(!CollectionUtils.isEmpty(headerMap)){
            headerMap.forEach((k,v)-> headers.set(k,v));
        }
        return restTemplate.postForObject(url,new HttpEntity<>(paramObjectStr,headers),String.class);
    }

    public static String putJson(String url, Map<String,String> headerMap, String paramObjectStr){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        if(!CollectionUtils.isEmpty(headerMap)){
            headerMap.forEach((k,v)-> headers.set(k,v));
        }
        HttpEntity<String> httpEntity = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(paramObjectStr,headers), String.class);
        return httpEntity.getBody();
    }

    public static String delete(String url, Map<String,String> headerMap, Map<String,String> paramMap){
        HttpHeaders headers = new HttpHeaders();
        if(!CollectionUtils.isEmpty(headerMap)){
            headerMap.forEach((k,v)-> headers.set(k,v));
        }
        StringBuffer paramStr = new StringBuffer(EMPTY_STR);
        if(!CollectionUtils.isEmpty(paramMap)){
            paramMap.forEach((k,v)->{
                if(paramStr.toString().equals(EMPTY_STR)){
                    paramStr.append("?").append(k).append("=").append(v);
                }else{
                    paramStr.append("&").append(k).append("=").append(v);
                }
            });
        }
        HttpEntity<String> httpEntity = restTemplate.exchange(url+paramStr.toString(), HttpMethod.DELETE, CollectionUtils.isEmpty(headerMap) ? null : new HttpEntity<>(headers),String.class);
        return httpEntity.getBody();
    }

    /**
     * post Form
     * @param url
     * @param headerMap
     * @param paramMap
     * @return
     */
    public static String postForm(String url, Map<String,String> headerMap, MultiValueMap<String,Object> paramMap){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        if(!CollectionUtils.isEmpty(headerMap)){
            headerMap.forEach((k,v)-> headers.set(k,v));
        }
        return restTemplate.postForObject(url,new HttpEntity<>(paramMap,headers),String.class);
    }
}

