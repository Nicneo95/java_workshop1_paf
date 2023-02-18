package com.example.workshop1.model;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

// 1. Start by creating  a customers model class for the table
public class Customers {

    // 2. List down the column name to create the table
    private Integer id;
    private String company;
    private String lastName;
    private String firstName;
    private String email;
    private String jobTitle;
    private String businessPhone;
    private String homePhone;
    private String mobilePhone;
    private String address;
    private String city;
    private String stateProvince;

    // 3. Generate getters and setters
    public Integer getCustomerId() {
        return id;
    }

    public void setCustomerId(Integer id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getBusinessPhone() {
        return businessPhone;
    }

    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    // 4. Take a SqlRowSet object as input and returns a Customers object
    public static Customers create(SqlRowSet rs) {
        Customers c = new Customers();
        c.setCustomerId(rs.getInt("id"));
        c.setCompany("company");
        c.setLastName(rs.getString("last_name"));
        c.setFirstName(rs.getString("first_name"));
        return c;
    }

    // 5. JSON object used to serialize the Customers object to JSON format
    public JsonObject toJSON() {
        return Json.createObjectBuilder()
                .add("id", getCustomerId())
                .add("company", getCompany())
                .add("last_name", getLastName())
                .add("first_name", getFirstName())
                .build();
    }

}
