package com.example.workshop1.repository;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.example.workshop1.model.Customers;
import com.example.workshop1.model.CustomersRowMapper;
import com.example.workshop1.model.Order;

// After coding model we create repo class to access data sources CRUD
@Repository
public class CustomersImpl implements CustomersRepo {
    
    // Query prepared statement 
    // ? as a placeholder for each parameter/value to bind
    public static final String SQL_SELECT_ALL_CUSTOMERS = "select id, company, last_name, first_name, email_address, job_title, business_phone, home_phone, mobile_phone, address, state_province from customers limit ?, ?";

    public static final String SQL_SELECT_CUSTOMERS_BY_ID = "select id, company, last_name, first_name, email_address, job_title, business_phone, home_phone, mobile_phone, address, state_province from customers where id = ?";

    public static final String SQL_SELECT_ALL_CUSTOMERS_ORDER = "select c.id as customer_id, company, c.last_name, c.first_name, c.email_address, c.job_title, c.business_phone, c.home_phone, c.mobile_phone, c.address, c.state_province, o.id as order_id, DATE_FORMAT(o.order_date, \"%d/%m/%Y\") as order_date, DATE_FORMAT(o.shipped_date, \"%d/%m/%Y\") as shipped_date, o.ship_name, o.shipping_fee from customers c, orders o where c.id = o.customer_id and o.customer_id = ?";

    @Autowired 
    JdbcTemplate jdbcTemplate;

    /*
     * Get All Customers record
     * 
     * @param offset
     * @param limit
     * @return
     */
    @Override
    public List<Customers> getAllCustomer(Integer offset, Integer limit) {
        
        // create a list to store customers result 
        final List<Customers> custs = new LinkedList<>();
        // SqlRowSet will hold the result for queryForRowSet
        final SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_SELECT_ALL_CUSTOMERS, offset, limit);
        // iterating over the result add into cust list
        while (rs.next()) {
            custs.add(Customers.create(rs));
        }
        return custs;
    }

    /*
     * Get single customer record by Id
     * 
     * @param id
     * @return
     */
    @Override
    public Customers findCustomerById(Integer id) {
        List<Customers> custs = jdbcTemplate.query(SQL_SELECT_CUSTOMERS_BY_ID, new CustomersRowMapper(),
                                        new Object[] {id});
        return custs.get(0); // get(0) - index 0 refer to the id 
    }

    @Override
    public List<Order> getCustomersOrder(Integer id) {
        // prevent inheritance
        final List<Order> orders = new LinkedList<>();

        // perform the query

        final SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_SELECT_ALL_CUSTOMERS_ORDER, id);

        while (rs.next()) {
            orders.add(Order.create(rs));
        }
        return orders;
    }
}
