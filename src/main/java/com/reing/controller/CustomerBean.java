package com.reing.controller;

import com.reing.model.entity.Customer;
import com.reing.service.impl.CustomerService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class CustomerBean implements Serializable {

    @Inject
    private CustomerService customerService;

    private List<Customer> customers;

    private Customer selectedCustomer;

    @PostConstruct
    public void init() {
        this.customers = this.customerService.getAll();
    }

    public Customer saveCustomer() {

        System.out.println(selectedCustomer);
        return null;
    }


    public List<Customer> getCustomers() {
        return customers;
    }

    public Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    public void setSelectedCustomer(Customer selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }

}
