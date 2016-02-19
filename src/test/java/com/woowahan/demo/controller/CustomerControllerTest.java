package com.woowahan.demo.controller;

import static jdk.nashorn.internal.runtime.ScriptRuntime.DELETE;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static
        org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.woowahan.SpringBootDemoApplication;
import com.woowahan.demo.domain.Customer;
import com.woowahan.demo.service.CustomerService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.JsonPathResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;



/**
 * Created by jhhan on 2016-02-12.
 * 업주관련 컨트롤러 클래스
 * TODO : 테스트를 위한 기본 목업 동작 확인
 * TODO : 업주조회 API
 * TODO : 업주목록 조회 API
 * TODO : 업주생성 API
 * TODO : 업주삭제 API
 * TODO : 업주갱신 API
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootDemoApplication.class)
@WebAppConfiguration
public class CustomerControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private CustomerService customerService;

    private static final MediaType mediaType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @After
    public void tearDown() {

    }

    /**
     * 기본 목업 동작이 잘 되는지 체크
     */
    @Test
    public void test_목업기본동작체크() throws Exception {
        mockMvc.perform(get("/test" ))
                .andExpect( status().isNotFound() );
    }

    /**
     * TODO : 고객조회를 위한 테스트 고객등록
     * TODO : 등록된 고객 id로 조회 목업작성
     * TODO : 리턴된 json과 값을 비교
     */
    @Test
    public void test_아이디로_고객_조회() throws Exception {
        Customer customer = new Customer(null, "한", "재현");
        Customer createdCustomer = this.customerService.create(customer);

        Long expectedId = createdCustomer.getId();

        mockMvc.perform( get("/customers/" + expectedId) )
                .andExpect(status().isOk() )
                .andExpect(content().contentType(mediaType) )
                .andExpect(
                        jsonPath("$.firstName").value(customer.getFirstName()))
                .andExpect(
                        jsonPath("$.lastName").value(customer.getLastName()))
                .andExpect(jsonPath("$.*",hasSize(3)))
                .andExpect(jsonPath("$.lastName",is(customer.getLastName())))
                .andExpect(jsonPath("$.lastName",is(any(String.class))))
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * TODO : 고객등록
     * TODO : 등록된 고객의 정보를 체크
     */
    @Test
    public void test_고객_등록() throws Exception {
        Customer customer = new Customer(null, "최", "인화");

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonCustomer = objectMapper.writeValueAsString(customer);

        System.out.println(jsonCustomer);

        mockMvc.perform(post("/customers").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonCustomer))
                .andExpect(jsonPath("$.firstName", is(customer.getFirstName())))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    /**
     * TODO : 고객 정보 수정(고객 등록 포함)
     * TODO : 등록된 고객 정보와 수정된 정보 확인
     */
    @Test
    public void test_고객_수정() throws Exception {
        Customer customer = new Customer(null, "손", "현태");
        Customer createdCustomer = customerService.create(customer);
        Customer updatedCustomer = new Customer(null, "민", "경수");

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonCustomer = objectMapper.writeValueAsString(updatedCustomer);

        mockMvc.perform(put("/customers/" + createdCustomer.getId()).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonCustomer))
                .andExpect(jsonPath("$.lastName", is(updatedCustomer.getLastName())))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

}