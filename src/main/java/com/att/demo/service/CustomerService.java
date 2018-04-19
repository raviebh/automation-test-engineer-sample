package com.att.demo.service;

import java.util.List;

import com.att.demo.model.Customer;

public interface CustomerService {
	
	Customer findById(long id);
	
	Customer findByName(String name);
	
	void saveCustomer(Customer customer);
	
	List<Customer> findAllCustomers();
	
	boolean isCustomerExistExist(Customer customer);
	
}
