package com.example.demo.entity;

import com.example.demo.config.MixPropertySourceFactory;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@ConfigurationProperties(prefix = "family")
//加载自定义配置文件 @PropertySource 默认不支持加载yml格式文件配置，需要自己写转化 MixPropertySourceFactory
@PropertySource(value = "classpath:family.yml", factory = MixPropertySourceFactory.class)
public class Family {
    private String name;
    private Father father;
    private Mother mother;
    private Child child;
}
