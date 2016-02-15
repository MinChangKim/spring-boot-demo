package com.woowahan.demo.service;

import com.woowahan.demo.domain.Customer;
import com.woowahan.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.transaction.Transactional;

/**
 * @Service
 * 서비스 클래스를 나타내는 애너테이션입니다. 기능 면에서는 @Component와 다르지 않습니다.
 * 서비스 클래스는 Repository가 올바르게 동작한다는 전제하에 구현하도록 합니다.
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

    public Customer read(long id) {
        return customerRepository.findOne(id);
    }

    /**
     * @Transactional = 메소드 단위의 BEGIN ~ COMMIT
     */
    @Transactional
    public Customer updateAll(long id, String fname, String lname) {
        Customer updateCustomer = customerRepository.findOne(id);
        updateCustomer.setFirstName(fname);
        updateCustomer.setLastName(lname);
        return updateCustomer;
    }

    /**
     * @Transactional = 메소드 단위의 BEGIN ~ COMMIT
     */
    @Transactional
    public Boolean delete(long id) {
        try{
            customerRepository.delete(id);
        }catch(EmptyResultDataAccessException e) {
            TransactionAspectSupport.currentTransactionStatus()
                    .setRollbackOnly();
            return false;
        }
        return true;
    }

    /**
     * @Transactional = 메소드 단위의 BEGIN ~ COMMIT
     * delete시  delete, delete2중  delete2 방법으로
     */
    @Transactional
    public boolean delete2(Long id) {
        Customer readed = customerRepository.findOne(id);
        boolean result;
        if(readed == null) {
            result = false;
        } else {
            customerRepository.delete(readed);
            result = true;
        }
        return result;
    }
}
