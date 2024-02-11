package com.example.demo.entity;

import com.example.demo.config.MixPropertySourceFactory;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;
import java.util.Map;

@Data
@Configuration
//@ConfigurationProperties(prefix = "employee")
//加载自定义配置文件 @PropertySource 默认不支持加载yml格式文件配置，需要自己写转化 MixPropertySourceFactory
@PropertySource(value = "classpath:employee.properties")
public class Employee {
    @Value("#{'${employee.names}'.split('\\|')}")
    private List<String> employeeNames;
    @Value("#{'${employee.names}'.split('\\|')[0]}")
    private String firstEmployeeName;
    @Value("#{${employee.age}}")
    private Map<String, String> employeeAgeMap;
    @Value("#{${employee.age}['two']}")
    private String employeeAgeTwo;
    @Value("#{${employee.age}['five']?: '99'}")
    private String employeeAgeWithDefaultValue;
    @Value("#{systemProperties['java.home']}")
    private String javaHome;
    @Value("#{systemProperties['user.dir']}")
    private String userDir;
}
