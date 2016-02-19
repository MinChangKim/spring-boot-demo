package com.woowahan.demo.controller;

import com.woowahan.demo.domain.Customer;
import com.woowahan.demo.service.CustomerService;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jhhan on 2016-02-12.
 */
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(method = RequestMethod.POST)
    public Customer postCustomer(@RequestBody Customer customer) {
        return customerService.create(customer);
    }

    @RequestMapping(value = "/{id}", method= RequestMethod.GET)
    public Customer getCustomerReadById(@PathVariable Long id) {
        return customerService.read(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Customer putCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        return customerService.updateAll(id, customer.getFirstName(), customer.getLastName());
    }
}
