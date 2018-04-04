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
import com.att.demo.model.User;
import com.att.demo.model.swagger.AccountResponse;
import com.att.demo.model.swagger.UserResponse;

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
@Api("user")
@Path("/users")
@Produces({MediaType.APPLICATION_JSON})
public interface UserResource {
	
    /**
     * Service definition which takes Account's ID and user Id as input 
     *
     * @param account Id - ID of the account being searched
     * 
     * @param user Id - ID of the user being searched
     * 
     * @return User - Returns the details of the users being searched
     */
	@GET
	@Path("/{accountId}/{userId}")
	@Produces({MediaType.APPLICATION_JSON})
	@ApiOperation(
			value = "Get User Resource",
			notes = "For the given account id and user id returns User resource",
			response = UserResponse.class)
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "OK"),
					@ApiResponse(code = 404, message = "Not Found")					
					})
	public Response getUser(
				@ApiParam(value = "Id of the account", required = true) 
				@PathParam("accountId")
				long accountId, @ApiParam(value = "Id of the user", required = true) 
				@PathParam("userId")
				long userId);
	
	/**
     * Service definition for User Creation inside a account
     *
     * @param user- Object instance of the User to be created
     * 
     * @param uriInfo - Request URI information
     * 
     * @return User - Returns the details of the User created
     */
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	@ApiOperation(
			value = "Create User",
			notes = "Create User, generates Id if not provided")
	@ApiResponses(
			value = {
					@ApiResponse(code = 201, message = "Created"),
					@ApiResponse(code = 404, message = "Account not found"),
					@ApiResponse(code = 409, message = "Conflict")					
					})
	public Response createUser(
				@ApiParam(value = "User to be created", required = true) 
				User user,
				@Context UriInfo uriInfo);
	
	
}