package com.att.demo.service;


import java.util.List;

import com.att.demo.model.Customer;

public interface CustomerService {
	
	Customer findById(long id);
	
	
	Customer findByFirstName(String firstName, String lastName);
	
	void saveCustomer(Customer customer);
	
	List<Customer> findAllCustomers();
	
	boolean isCustomerExist(Customer customer);
	
}
