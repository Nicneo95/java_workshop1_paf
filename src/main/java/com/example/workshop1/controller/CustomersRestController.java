package com.example.workshop1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.workshop1.model.Customers;
import com.example.workshop1.model.Order;
import com.example.workshop1.repository.CustomersImpl;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

@RestController
@RequestMapping(path = "/api/customer", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomersRestController {

    @Autowired
    private CustomersImpl customersImpl;

    @GetMapping()
    public ResponseEntity<String> getAllCustomer(@RequestParam(required = false) String limit,
            @RequestParam(required = false) String offset) {
        // Query the database
        List<Customers> customers = customersImpl.getAllCustomer(Integer.parseInt(offset),
                Integer.parseInt(limit));

        // Build the result
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Customers c : customers)
            arrayBuilder.add(c.toJSON());
        JsonArray result = arrayBuilder.build();
        System.out.println("" + result.toString());
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(result.toString());
    }

    @GetMapping(path = "{customerId}")
    public ResponseEntity<String> getCustomerById(@PathVariable Integer customerId) {
        System.out.println("Get customer by id");
        JsonObject result = null;
        try {
            // query the database for the books
            Customers customer = customersImpl.findCustomerById(customerId);

            // build the result
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("customer", customer.toJSON());
            result = objectBuilder.build();
        } catch (IndexOutOfBoundsException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error_message\": \"record not found\"}");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(result.toString());
    }

    @GetMapping(path = "{customerId}/orders")
    public ResponseEntity<String> getCustomersOrder(@PathVariable Integer customerId) {
        System.out.println("Get customer's order");
        JsonArray result = null;
        try {
            // Query the database for the books
            List<Order> orders = customersImpl.getCustomersOrder(customerId);

            // Build the result
            JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
            for (Order o : orders)
                arrBuilder.add(o.toJSON());
            result = arrBuilder.build();
        } catch (IndexOutOfBoundsException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error_mesg\": \"record not found\"}");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(result.toString());

    }

}