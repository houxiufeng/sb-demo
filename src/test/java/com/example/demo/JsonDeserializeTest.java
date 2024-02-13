package com.example.demo;

import com.example.demo.common.JacksonUtil;
import com.example.demo.entity.CustomField;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

public class JsonDeserializeTest {

    @Test
    public void test1() {
        String pklist = "{\"name\":\"my-pklist\",\"value\":\"allen\",\"type\":\"PickList\"}";
        String url = "{\"name\":\"my-url\",\"value\":\"www.baidu.com\",\"type\":\"URL\"}";
        String uuid = "{\"name\":\"my-uuid\",\"value\":\"12345697799\",\"type\":\"UUID\"}";
        String multiSelect = "{\"name\":\"kings\",\"value\":\"fitz\",\"options\":[\"allen\",\"fitz\",\"elvis\"],\"type\":\"MultiSelect\"}";

        List<String> list = Lists.newArrayList(pklist, url, uuid, multiSelect);
        List<CustomField> collect = list.stream().map(json -> JacksonUtil.json2Bean(json, CustomField.class)).collect(Collectors.toList());
        System.out.println(collect);
        System.out.println(collect.get(0).getName() + ", " + collect.get(0).getValue() + ", " + collect.get(0).getType());
        System.out.println(collect.get(1).getName() + ", " + collect.get(1).getValue() + ", " + collect.get(1).getType());
        System.out.println(collect.get(2).getName() + ", " + collect.get(2).getValue() + ", " + collect.get(2).getType());
        System.out.println(collect.get(3).getName() + ", " + collect.get(3).getValue() + ", " + collect.get(3).getType());

        System.out.println("-------------------------------");
        for (CustomField customField : collect) {
            System.out.println(JacksonUtil.bean2Json(customField));
        }

    }
}
