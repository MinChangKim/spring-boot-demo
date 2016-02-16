package com.woowahan.demo.controller;

import com.woowahan.SpringBootDemoApplication;
import com.woowahan.demo.domain.Customer;
import com.woowahan.demo.service.CustomerService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by jhhan on 2016-02-12.
 * 업주관련 컨트롤러 클래스
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
     * Todo 리팩토링 필요
     * @throws Exception
     */
    @Test
    public void testCustomerReadById_Customers의_Value_를_체크한다() throws Exception {
        Customer customer = new Customer(null, "한", "재현");
        Customer createdCustomer = this.customerService.create(customer);

        Long expectedId = createdCustomer.getId();

        mockMvc.perform( get("/customers/"+expectedId) )
                .andExpect( status().isOk() )
                .andExpect( content().contentType(mediaType) )
                .andExpect( content().string(containsString("재현")) );

    }

}