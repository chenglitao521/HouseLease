package com.xiamo.common.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * <dl>
 * <dt>Test</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company:  程立涛</dd>
 * <dd>CreateDate: 2018/1/2 0002</dd>
 * </dl>
 *
 * @author chenglitao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-mvc.xml", "classpath*:applicationContext.xml", "classpath*:applicationContext_*.xml"})
//配置事务的回滚,对数据库的增删改都会回滚,便于测试用例的循环利用
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@WebAppConfiguration
public class UserTest {

    //记得配置log4j.properties ,的命令行输出水平是debug
    protected Log logger = LogFactory.getLog(UserTest.class);

    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Before()  //这个方法在每个方法执行之前都会执行一遍
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  //初始化MockMvc对象
    }

    @Test
    public void testAllCategoryTest() throws Exception {
        String s = mockMvc.perform(
                get("/privilege/query")    //请求的url,请求的方法是get
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED).param("page", "1").param("rows", "2").param("id", "1")  //数据的格式
                //添加参数
        ).andExpect(status().isOk())    //返回的状态是200
                .andReturn().getResponse().getContentAsString();    //打印出请求和相应的内容
        //将相应的数据转换为字符串
        System.out.println(s);
        String requestBody = "{\"page\":1, \"rows\":2}";


/*
        String responseString = mockMvc.perform(post("/privilege/query")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED).content(requestBody)).andDo(print())
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
*/


    }
}
