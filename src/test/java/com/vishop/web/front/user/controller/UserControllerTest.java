package com.vishop.web.front.user.controller;

import com.vishop.BaseTest;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created with vishop.
 * User : Homiss
 * Date : 2016/1/12
 * Time : 21:20
 */

public class UserControllerTest extends BaseTest{

    private static Logger logger = Logger.getLogger(UserControllerTest.class);

    // 模拟request,response
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private MockHttpSession session;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp(){
        logger.info("用户测试开始...");
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
        session = new MockHttpSession();
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    /**
     * 测试用户注册
     * @throws Exception
     */
    @Test
    @Rollback(true)
    public void registTest() throws Exception{
        String name = "Homiss";
        mockMvc.perform((post("/user/regist")
                .param("username", name)
                .param("password", "123456")
                .param("repassword", "123456")
        )).andExpect(status().isOk())
                .andDo(print());
    }

    /**
     * 用户登录
     */
    @Test
    @Rollback(false)
    public void loginTest() throws Exception{
        String username = "Homiss";
        String password = "123456";
        mockMvc.perform((post("/user/login")
                .param("username", username)
                .param("password", password)
        )).andExpect(status().isOk())
                .andDo(print());
    }

    @After
    public void destory(){
        logger.info("用户测试结束...");
    }


}
