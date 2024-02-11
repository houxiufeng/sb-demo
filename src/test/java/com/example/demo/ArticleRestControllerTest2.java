package com.example.demo;

import com.example.demo.controller.ArticleController;
import com.example.demo.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@Slf4j
@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ArticleRestControllerTest2 {

    @Autowired
    private MockMvc mockMvc;

    /**
     * servlet 容器下的测试。需要加入@AutoConfigureMockMvc @SpringBootTest @ExtendWith(SpringExtension.class) 这几个注解
     * 这样的化， controller 里面的依赖注入方法才能生效。
     * @throws Exception
     */



    @Test
    public void testSaveArticle() throws Exception {
        String article = "{\"id\":1,\"author\":\"zimug\",\"title\":\"springboot study\",\"content\":\"spring boot 从青铜到王者\",\"createTime\":1707394099778,\"reader\":[{\"name\":\"allen\",\"age\":12},{\"name\":\"fitz\",\"age\":23}]}";
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.request(HttpMethod.POST, "/rest/articles")
                .contentType("application/json")
                .header("aaa", "123456")
                .content(article);
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
