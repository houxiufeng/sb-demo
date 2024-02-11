package com.example.demo;

import com.example.demo.controller.ArticleController;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@Slf4j
public class ArticleRestControllerTest {

    private static MockMvc mockMvc;


    /**
     * @BeforeAll 注解表示在所有测试方法执行之前执行一次，并且必须是静态方法。通常用于初始化测试环境
     * @BeforeEach 注解表示在每个测试方法执行之前执行一次。通常用于初始化测试数据或者创建被测试对象的实例。与 @BeforeAll 不同，@BeforeEach 方法不能是静态的。
     */
    @BeforeAll
    static void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new ArticleController()).build();
    }

    @Test
    public void testSaveArticle() throws Exception {
//        String article = "{\n" +
//                "    \"id\": 1,\n" +
//                "    \"author\": \"zimug\",\n" +
//                "    \"title\": \"springboot study\",\n" +
//                "    \"content\": \"spring boot 从青铜到王者\",\n" +
//                "    \"createTime\": 1707394099778,\n" +
//                "    \"reader\": [\n" +
//                "        {\n" +
//                "            \"name\": \"allen\",\n" +
//                "            \"age\": 12\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"name\": \"fitz\",\n" +
//                "            \"age\": 23\n" +
//                "        }\n" +
//                "    ]\n" +
//                "}";
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
