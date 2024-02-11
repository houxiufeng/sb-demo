package com.example.demo;

import com.example.demo.vos.AjaxResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

public class JacksonTest {

    @Test
    public void testJackson() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        AjaxResponse result = AjaxResponse.success("test");
        String json = mapper.writeValueAsString(result);
        System.out.println(json);
        AjaxResponse result1 = mapper.readValue(json, AjaxResponse.class);
        System.out.println(result1);
    }

    @Test
    public void testProcessor() {
        int i = Runtime.getRuntime().availableProcessors();
        System.out.println(i);

    }
}
