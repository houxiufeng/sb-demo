package com.example.demo;

import com.example.demo.common.JacksonUtil;
import com.example.demo.controller.MockController;
import com.example.demo.entity.Article;
import com.example.demo.service.MockService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@Slf4j
@AutoConfigureMockMvc
//@SpringBootTest 会对所有注解尝试注入，范围太大.
//@SpringBootTest
//只针对MockController.class 进行测试
@WebMvcTest(value = MockController.class)
@ExtendWith(SpringExtension.class)
public class MockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MockService mockService;

    /**
     * 模拟get请求
     * mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", userId));
     * 模拟post请求
     * mockMvc.perform(MockMvcRequestBuilders.post("uri", parameters));
     * 模拟文件上传
     * mockMvc.perform(MockMvcRequestBuilders.multipart("uri").file("fileName", "file".getBytes("UTF-8")));
     * 模拟session和cookie
     * mockMvc.perform(MockMvcRequestBuilders.get("uri").sessionAttr("name", "value"));
     * mockMvc.perform(MockMvcRequestBuilders.get("uri").cookie(new Cookie("name", "value")));
     * 设置http header
     * mockMvc.perform(MockMvcRequestBuilders
     *     .get("uri", parameters)
     *     .contentType("application/json")
     *     .accept("application/json")
     *     .header("key","value")
     * );
     * @throws Exception
     */



    @Test
    public void testSaveArticle() throws Exception {
        String article = "{\"id\":1,\"author\":\"zimug\",\"title\":\"springboot study\",\"content\":\"spring boot 从青铜到王者\",\"createTime\":1707394099778,\"reader\":[{\"name\":\"allen\",\"age\":12},{\"name\":\"fitz\",\"age\":23}]}";
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.request(HttpMethod.POST, "/mock/articles")
                .contentType("application/json")
                .header("aaa", "123456")
                .content(article);
        Article article1 = JacksonUtil.json2Bean(article, Article.class);
        when(mockService.saveArticle(article1)).thenReturn("ok");
        ResultActions resultActions = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.author").value("zimug"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.reader[0].name").value("allen"));
        MockHttpServletResponse response = resultActions.andReturn().getResponse();
        response.setCharacterEncoding("UTF-8");
        resultActions.andDo(print());
        log.info("---->" + response.getContentAsString());

    }
}
