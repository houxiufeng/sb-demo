package com.example.demo.entity;

import com.example.demo.config.MixPropertySourceFactory;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@ConfigurationProperties(prefix = "home")
//加载自定义配置文件 @PropertySource 支持properties文件
@PropertySource(value = "classpath:home.properties")
public class Home {
    private String name;
    private String address;
    private Integer number;
    private Boolean isCap;
}
