package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.util.json.JsonFileUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("local")
public class JpaMongoCreateTests {

	private MockMvc mvc;
	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setUp(){
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void invokeTestCase() throws Exception {
	    // 读取 json 文件内容
		JSONObject bodyJSON = JsonFileUtils.readJSONObject("classpath:testcases/json/JpaMongoCreate.json");
		Assert.assertNotNull(bodyJSON);

		String body = mapper.writeValueAsString(bodyJSON);

		// 组装 querystring
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("name", "union1");

		// 构造请求
		RequestBuilder request = post("/jpa/mongo/create")
                                    .params(params)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(body);

		// 发送请求并返回结果
		MvcResult result = mvc.perform(request).andExpect(status().isOk()).andReturn();
		MockHttpServletResponse response = result.getResponse();
		Assert.assertNotNull(response);
	}
}
