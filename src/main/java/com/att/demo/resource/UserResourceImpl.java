package com.att.demo.resource;

import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import com.att.demo.exception.CustomError;
import com.att.demo.model.Account;
import com.att.demo.model.User;
import com.att.demo.model.representation.Resource;
import com.att.demo.service.AccountService;
import com.att.demo.service.UserService;

@Controller
public class UserResourceImpl implements UserResource {

	public static final Logger logger = LoggerFactory.getLogger(UserResourceImpl.class);

	@Autowired
	AccountService accountService; // Service which will do all data retrieval/manipulation work

	@Autowired
	UserService userService; // Service which will do all data retrieval/manipulation work

	private static String baseUrl = "/users";

	@Override
	public Response getUser(long accountId, long userId) {
		logger.info("Fetching User with account id {} and user id {} ", accountId, userId);
		User user = userService.findById(accountId, userId);
		if (user == null) {
			logger.error("User with id {} not found.", userId);
			return Response.status(HttpStatus.NOT_FOUND.value())
					.entity(new CustomError("User with id " + userId + " not found", HttpStatus.NOT_FOUND.name()))
					.build();
		}

		Link link = Link.fromUri(baseUrl).rel("self").build(accountId);

		Resource<User> resource = new Resource<>(user);
		return Response.ok(resource).links(link).build();
	}

	@Override
	public Response createUser(User user, UriInfo uriInfo) {
		logger.info("Creating User : {}", user);

		if (accountService.findById(user.getAccountId()) == null) {
			logger.error("Unable to create. Account id {} does not exist", user.getAccountId());

			return Response.status(HttpStatus.NOT_FOUND.value())
					.entity(new CustomError("Unable to create. Account does not exist ", HttpStatus.NOT_FOUND.name()))
					.build();

		}

		if (userService.isUserExistExist(user)) {
			logger.error("Unable to create. User with name {} already exist", user.getName());
			// .............
			return Response.status(HttpStatus.CONFLICT.value())
					.entity(new CustomError("Unable to create. Usert with name " + user.getName() + " already exist",
							HttpStatus.CONFLICT.name()))
					.build();

		}

		userService.saveUser(user);
		UriBuilder builder = uriInfo.getRequestUriBuilder();
		builder.path(String.valueOf(user.getId()));
		return Response.created(builder.build()).build();
	}

}
