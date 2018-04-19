package com.att.demo.resource;

import java.util.List;

import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.att.demo.exception.CustomError;
import com.att.demo.model.Customer;
import com.att.demo.model.representation.Resource;
import com.att.demo.model.representation.ResourceCollection;
import com.att.demo.service.CustomerService;

/**
 * This is the Controller class for Account mService
 * 
 * 
 */
@Controller
public class CustomerResourceImpl implements CustomerResource {
	
	public static final Logger logger = LoggerFactory.getLogger(AccountResourceImpl.class);

	@Autowired
	CustomerService customerService; //Service which will do all data retrieval/manipulation work

	private static String baseUrl = "/accounts";

	@Override
	public Response findAllCustomers() {
		List<Customer> customers = customerService.findAllCustomers();		
		if (customers == null) {
			return Response.noContent().build();
		}		
		Link link = Link.fromUri(baseUrl).rel("self").build();		
		ResourceCollection<Customer> resource = new ResourceCollection<>(customers);
		return Response.ok(resource).links(link).build();
	}
	
	@Override
	public Response getCustomer(long customerId) {
		
		logger.info("Fetching Account with id {}", customerId);
		Customer customer = customerService.findById(customerId);
		if (customer == null) {
			logger.error("Account with id {} not found.", customerId);	
			return Response.status(HttpStatus.NOT_FOUND.value()).entity(new CustomError("Customer with id " + customerId 
					+ " not found", HttpStatus.NOT_FOUND.name())).build();			
		}	

		Link link = Link.fromUri(baseUrl).rel("self").build(customerId);

		Resource<Customer> resource = new Resource<>(customer);
		return Response.ok(resource).links(link).build();
	}

	@Override
	public Response createCustomer(Customer customer, UriInfo uriInfo) {
		logger.info("Creating Account : {}", customer);

		if (customerService.isCustomerExist(customer)) {
			logger.error("Unable to create. A Account with name {} already exist", customer.getFirstName());
			//.............
			return Response.status(HttpStatus.CONFLICT.value()).entity(new CustomError("Unable to create. A Account with name " + customer.getFirstName() 
					+ " already exist", HttpStatus.CONFLICT.name())).build();
			
		}
		customerService.saveCustomer(customer);
		UriBuilder builder = uriInfo.getRequestUriBuilder();
		builder.path(String.valueOf(customer.getId()));
		return Response.created(builder.build()).build();
	}

	

}