package com.crazysalaryman.service;

import com.crazysalaryman.domain.Customer;
import com.crazysalaryman.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lse0101 on 2017-01-23.
 */
@Service
@Transactional
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;


    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findOne(Integer id){
        return customerRepository.findOne(id);
    }

    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer update(Customer customer) {
        return customerRepository.save(customer);
    }

    public void delete(Integer id) {
        customerRepository.delete(id);
    }
}
