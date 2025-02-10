package com.reing.service.impl;

import com.reing.model.entity.Customer;
import com.reing.repository.CustomerRepository;
import com.reing.service.ICustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAll() {
        return (List<Customer>) customerRepository.findAll();
    }
}
