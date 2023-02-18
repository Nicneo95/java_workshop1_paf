package com.example.workshop1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.workshop1.model.Customers;
import com.example.workshop1.model.Order;
import com.example.workshop1.repository.CustomersRepo;

@Service
public class CustomersService {
    
    @Autowired
    CustomersRepo customersRepo;

    public List<Customers> getAllCustomer(Integer offset, Integer limit) {
        return customersRepo.getAllCustomer(offset, limit);
    }

    public Customers findCustomerById(Integer id) {
        return customersRepo.findCustomerById(id);
    }

    public List<Order> getCustomersOrder(Integer id) {
        return customersRepo.getCustomersOrder(id);
    }
    
}
