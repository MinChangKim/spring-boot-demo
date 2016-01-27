package com.woowahan.demo.service;

import com.woowahan.demo.domain.Customer;
import com.woowahan.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by sykim on 2016. 1. 26..
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * @Transactional = 메소드 단위의 BEGIN ~ COMMIT
     */
    @Transactional
    public Customer create(Customer customer) {
        // JPA에서는 넣을 때 Entity 객체와 리턴 받은 Entity 객체가 동기화 된다.
        return customerRepository.save(customer);
    }
}
