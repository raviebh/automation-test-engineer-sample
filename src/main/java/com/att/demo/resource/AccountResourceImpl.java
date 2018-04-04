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
import com.att.demo.model.Account;
import com.att.demo.model.representation.Resource;
import com.att.demo.model.representation.ResourceCollection;
import com.att.demo.service.AccountService;

/**
 * This is the Controller class for Account mService
 * 
 * 
 */
@Controller
public class AccountResourceImpl implements AccountResource {
	
	public static final Logger logger = LoggerFactory.getLogger(AccountResourceImpl.class);

	@Autowired
	AccountService accountService; //Service which will do all data retrieval/manipulation work

	private static String baseUrl = "/accounts";

	@Override
	public Response findAllAccounts() {
		List<Account> accounts = accountService.findAllAccounts();		
		if (accounts == null) {
			return Response.noContent().build();
		}		
		Link link = Link.fromUri(baseUrl).rel("self").build();		
		ResourceCollection<Account> resource = new ResourceCollection<>(accounts);
		return Response.ok(resource).links(link).build();
	}
	
	@Override
	public Response getAccount(long accountId) {
		
		logger.info("Fetching Account with id {}", accountId);
		Account account = accountService.findById(accountId);
		if (account == null) {
			logger.error("Account with id {} not found.", accountId);	
			return Response.status(HttpStatus.NOT_FOUND.value()).entity(new CustomError("Account with id " + accountId 
					+ " not found", HttpStatus.NOT_FOUND.name())).build();			
		}	

		Link link = Link.fromUri(baseUrl).rel("self").build(accountId);

		Resource<Account> resource = new Resource<>(account);
		return Response.ok(resource).links(link).build();
	}

	@Override
	public Response createAccount(Account account, UriInfo uriInfo) {
		logger.info("Creating Account : {}", account);

		if (accountService.isAccountExist(account)) {
			logger.error("Unable to create. A Account with name {} already exist", account.getName());
			//.............
			return Response.status(HttpStatus.CONFLICT.value()).entity(new CustomError("Unable to create. A Account with name " + account.getName() 
					+ " already exist", HttpStatus.CONFLICT.name())).build();
			
		}
		accountService.saveAccount(account);
		UriBuilder builder = uriInfo.getRequestUriBuilder();
		builder.path(String.valueOf(account.getId()));
		return Response.created(builder.build()).build();
	}

	

}