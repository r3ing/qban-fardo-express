package com.reing.repository;

import com.reing.model.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository <Customer, Long> {
}
