package com.example.workshop1.repository;

import java.util.List;

import com.example.workshop1.model.Customers;
import com.example.workshop1.model.Order;

public interface CustomersRepo {

    // Get all customers record
    public List<Customers> getAllCustomer(Integer offset, Integer limit);

    // Get a single record by customer id
    public Customers findCustomerById(Integer id);

    // Get all orders of a customer
    public List<Order> getCustomersOrder(Integer id);
    
}
