package com.att.demo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.att.demo.model.Customer;



@Service("customerService")
public class CustomerServiceImpl implements CustomerService{
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<Customer> customers;
	
	static{
		customers= populateDummyCustomers();
	}

	public List<Customer> findAllCustomers() {
		return customers;
	}
	
	public Customer findById(long id) {
		for(Customer customer : customers){
			if(customer.getId() == id){
				return customer;
			}
		}
		return null;
	}
	
	public Customer findByFirstName(String firstName, String lastName) {
		for(Customer customer : customers){
			if(customer.getFirstName().equalsIgnoreCase(firstName) && customer.getLastName().equalsIgnoreCase(lastName) ){
				return customer;
			}
		}
		return null;
	}
	
	public void saveCustomer(Customer customer) {
		counter.incrementAndGet();
		customers.add(customer);
	}

	public void updateCustomer(Customer customer) {
		int index = customers.indexOf(customer);
		customers.set(index, customer);
	}

	
	public boolean isCustomerExist(Customer customer) {
		return findById(customer.getId())!=null ;
	}
	
	private static List<Customer> populateDummyCustomers(){
		List<Customer> customers = new ArrayList<Customer>();
		customers.add(new Customer(counter.incrementAndGet(),"CustomerFirst1","CustomerLast1"));
		customers.add(new Customer(counter.incrementAndGet(),"Customer2","CustomerLast2"));
		customers.add(new Customer(counter.incrementAndGet(),"Customer3","CustomerLast3"));
		return customers;
	}

}
