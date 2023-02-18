package com.example.workshop1.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
// show how to query using custom rowmapper 
public class CustomersRowMapper implements RowMapper<Customers> {

    @Override
    public Customers mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customers c = new Customers();
        c.setCustomerId(rs.getInt("id"));
        c.setCompany("company");
        c.setLastName(rs.getString("last_name"));
        c.setFirstName(rs.getString("first_name"));
        c.setEmail(rs.getString("email_address"));
        c.setJobTitle(rs.getString("job_title"));
        c.setBusinessPhone(rs.getString("business_phone"));
        c.setHomePhone(rs.getString("home_phone"));
        c.setMobilePhone(rs.getString("mobile_phone"));
        c.setAddress(rs.getString("address"));
        c.setStateProvince(rs.getString("state_province"));
        return c;
    }
    
}
