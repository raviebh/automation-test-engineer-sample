package com.att.demo.model.swagger;

import com.att.demo.model.Customer;
import com.att.demo.model.representation.Resource;

/**
 * Swagger response class for the Account model object.
 * @param <T>
 *
 * @param <T>
 */
public class CustomerResponse extends Resource<Customer> {
	
    /**
     * This constructor sets the content of the Account Response 
     * 
     * @param content Command line parameters if any
     * 
     */
	public CustomerResponse(Customer content) {
		super(content);
	}
	

    /**
     * This method gets the content of the Account Response 
     * 
     * @return content of the user Response
     * 
     */
	@Override
	public Customer getContent() {
		return super.getContent();
	}

}