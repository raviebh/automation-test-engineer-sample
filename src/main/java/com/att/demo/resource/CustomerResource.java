package com.att.demo.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.att.demo.model.Customer;
import com.att.demo.model.swagger.CustomerResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
 * This is the Interface definition for Account mService
 * 
 * 
 */
@Api("customers")
@Path("/customers")
@Produces({MediaType.APPLICATION_JSON})
public interface CustomerResource {
	
    /**
     * Service definition which returns all the customers
     *
     * 
     * @return User - Returns the details of the customers being searched
     */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	@ApiOperation(
			value = "Get Customer Resource",
			notes = "Returns all the Customers in ResourceCollection representation format",
			response = CustomerResponse.class)
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "OK"),
					@ApiResponse(code = 204, message = "No Content")					
					})
	public Response findAllCustomers();
	
    /**
     * Service definition which takes Customer's ID as input 
     *
     * @param customer Id - ID of the account being searched
     * 
     * @return User - Returns the details of the customers being searched
     */
	@GET
	@Path("/{customerId")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	@ApiOperation(
			value = "Get Customer Resource",
			notes = "For the given customer id returns customer resource",
			response = CustomerResponse.class)
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "OK"),
					@ApiResponse(code = 404, message = "Not Found")					
					})
	public Response getCustomer(
				@ApiParam(value = "Id of the Customer to retrieve", required = true) 
				@PathParam("customerId")
				long customerId);
	
	/**
     * Service definition for Customer Creation
     *
     * @param user- Object instance of the Customer to be created
     * 
     * @param uriInfo - Request URI information
     * 
     * @return User - Returns the details of the Customer created
     */
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	@ApiOperation(
			value = "Create Customer",
			notes = "Create Customer, generates Id if not provided")
	@ApiResponses(
			value = {
					@ApiResponse(code = 201, message = "Created"),
					@ApiResponse(code = 409, message = "Conflict")					
					})
	
	public Response createCustomer(
				@ApiParam(value = "User to be created", required = true) 
				Customer customer,
				@Context UriInfo uriInfo);
	
	
}