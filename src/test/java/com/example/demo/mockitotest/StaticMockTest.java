package com.example.demo.mockitotest;

import com.example.demo.common.RestTemplateUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static org.mockito.Mockito.any;

@ExtendWith(MockitoExtension.class)
public class StaticMockTest {
    /**
     * mock静态方法：需要加入以下依赖来支持static method 测试
     * <dependency>
     *     <groupId>org.mockito</groupId>
     *     <artifactId>mockito-inline</artifactId>
     *     <version>3.8.0</version>
     *     <scope>test</scope>
     * </dependency>
     */
    @Test
    public void test1() {
        HashMap<String, String> params = new HashMap<>();
        MockedStatic<RestTemplateUtil> mockRestTemplateUtil = Mockito.mockStatic(RestTemplateUtil.class);
        mockRestTemplateUtil.when(() -> RestTemplateUtil.get("url",null, params)).thenReturn("test");
        String s = RestTemplateUtil.get("url", null, params);
        Assertions.assertEquals(s, "test");
    }

}
