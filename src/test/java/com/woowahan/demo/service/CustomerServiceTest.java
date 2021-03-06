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

    private Customer lastCreated;

    @Before
    public void setUp() throws Exception {
        Customer customer = new Customer(null, "강", "뷰티");
        this.lastCreated = customerService.create(customer);
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
        Customer createdCustomer =
                customerService.create(new Customer(null, "승", "발대"));
        assertEquals(new Customer(createdCustomer.getId(), "승", "발대"),
                createdCustomer);
    }

    /**
     * 고객조회(Select) Service 만들기
     * 예 : USP_Super_Customer_S01
     */
    @Test
    public void testCustomerRead() {
        // OUTPUT : Customer
        Long id = this.lastCreated.getId();
        Customer readed = customerService.read(id);
        assertEquals(new Customer(id, "강", "뷰티" ), readed);
    }

    @Test
    public void testCustomerUpdate() {

    }

    @Test
    public void testA() { }
}