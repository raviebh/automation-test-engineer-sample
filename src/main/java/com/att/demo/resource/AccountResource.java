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

import com.att.demo.model.Account;
import com.att.demo.model.swagger.AccountResponse;

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
@Api("account")
@Path("/accounts")
@Produces({MediaType.APPLICATION_JSON})
public interface AccountResource {
	
    /**
     * Service definition which returns all the accounts
     *
     * 
     * @return User - Returns the details of the accounts being searched
     */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	@ApiOperation(
			value = "Get Account Resource",
			notes = "Returns all the accounts in ResourceCollection representation format",
			response = AccountResponse.class)
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "OK"),
					@ApiResponse(code = 204, message = "No Content")					
					})
	public Response findAllAccounts();
	
    /**
     * Service definition which takes Account's ID as input 
     *
     * @param account Id - ID of the account being searched
     * 
     * @return User - Returns the details of the accounts being searched
     */
	@GET
	@Path("/{accountId}")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	@ApiOperation(
			value = "Get Account Resource",
			notes = "For the given account id returns account resource",
			response = AccountResponse.class)
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "OK"),
					@ApiResponse(code = 404, message = "Not Found")					
					})
	public Response getAccount(
				@ApiParam(value = "Id of the account to retrieve", required = true) 
				@PathParam("accountId")
				long accountId);
	
	/**
     * Service definition for Account Creation
     *
     * @param user- Object instance of the Account to be created
     * 
     * @param uriInfo - Request URI information
     * 
     * @return User - Returns the details of the account created
     */
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	@ApiOperation(
			value = "Create Account",
			notes = "Create Account, generates Id if not provided")
	@ApiResponses(
			value = {
					@ApiResponse(code = 201, message = "Created"),
					@ApiResponse(code = 409, message = "Conflict")					
					})
	public Response createAccount(
				@ApiParam(value = "User to be created", required = true) 
				Account account,
				@Context UriInfo uriInfo);
	
	
}