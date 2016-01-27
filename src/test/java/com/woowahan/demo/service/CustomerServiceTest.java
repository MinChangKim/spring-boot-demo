package com.woowahan.demo.service;

import static org.junit.Assert.assertEquals;

import com.woowahan.SpringBootDemoApplication;
import com.woowahan.demo.domain.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;



/**
 * Created by sykim on 2016. 1. 26..
 * TODO : 고객생성(Create) Service 만들기
 * TODO : 고객조회
 * TODO : 고객수정
 * TODO : 고객삭제
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootDemoApplication.class)
@WebAppConfiguration
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    /**
     * 고객생성(Create) Service 만들기
     * 예 : USP_Super_Customer_M01
     */
    @Test
    public void testCustomerCreate() {
        // OUTPUT : 어떤 데이터가 나와야 할까?
        Customer customer = new Customer(null, "강", "뷰티");
        Customer created = customerService.create(customer);
        assertEquals(new Customer(1L, "강", "뷰티"), created);
    }
}