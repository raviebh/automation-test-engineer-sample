package com.att.demo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.att.demo.model.Customer;


@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	private static final AtomicLong counter = new AtomicLong();

	private static List<Customer> customers;

	static {
		customers = populateDummyCustomers();

	}

	private static List<Customer> populateDummyCustomers() {
		List<Customer> customers = new ArrayList<Customer>();
		customers.add(new Customer(1,"Customer1"));
		customers.add(new Customer(2, "Customer2"));
		customers.add(new Customer(3, "Customer3"));
		return customers;
	}

	@Override
	public Customer findById(long customerId) {
		for (Customer customer : customers) {
			if (customer.getId() == customerId) {
				return customer;
			}
		}

		return null;
	}
	
	@Override
	public Customer findByName(String name) {
		for (Customer customer : customers) {
			if (customer.getName() == name) {
				return customer;
			}
		}

		return null;
	}

	@Override
	public void saveCustomer(Customer customer) {
		customer.setId(counter.incrementAndGet());
		customers.add(customer);

	}

	@Override
	public List<Customer> findAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCustomerExistExist(Customer customer) {
		return findById(customer.getId()) != null;
	}

}
