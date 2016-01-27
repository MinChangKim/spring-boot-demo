package com.woowahan.demo.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


/**
 * Created by anyjava on 2016. 1. 25..
 */
public class CustomerTest {

    @Test
    public void testCustomer() {

        Customer customer = new Customer(1L, "손", "현태");

        assertEquals(Long.valueOf(1L), customer.getId());
        assertEquals("손", customer.getFirstName());
        assertEquals("현태", customer.getLastName());
    }
}