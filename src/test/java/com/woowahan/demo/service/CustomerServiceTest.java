package com.woowahan.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
 * DONE : 고객생성(Create) Service 만들기
 * DONE : 고객조회(Read)
 * DONE : 고객수정(Update)
 * DONE : 고객삭제(Delete)
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

    /**
     * 고객수정(Update) Service 만들기
     * 예 : USP_Super_Customer_M02
     */
    @Test
    public void testCustomerUpdate() {
        Customer updated = customerService.updateAll(1L, "민", "경수");
        assertEquals(new Customer(1L, "민", "경수"), updated);
    }

    /**
     * 고객삭제(Delete) Service 만들기
     * 예 : USP_Super_Customer_M03
     */
    @Test
    public void testCustomerDelete() {
        Long id = this.lastCreated.getId();
        assertTrue(customerService.delete(id));
        assertFalse(customerService.delete(id));
        assertFalse(customerService.delete2(id));
    }

    @Test
    public void testA() { }
}