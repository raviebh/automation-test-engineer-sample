package com.att.demo.integration;

import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

/**
 * @author katkah(Harish katkam)
 * User Service Integration Test cases.
 *
 */

public class UserServiceIT {

	private String uri = "/users";

	@Before
	public void setUp() throws Exception {
		String baseURI = "http://localhost:8080";
		// System.getProperty("BASE_URL");
		RestAssured.baseURI = baseURI + "/api";
	}

	private RequestSpecification givenBaseSpec() {
		return RestAssured.given().accept(ContentType.JSON).contentType(ContentType.JSON);
	}

	@Test
	/***
	 * Get user
	 */
	public void testGetUser_success() {

		String getUserURI = "/users/{accountId}/{userId}";

		givenBaseSpec().pathParam("accountId", 0).pathParam("userId", 1).when().get(getUserURI).then().statusCode(200)
				.and().contentType(ContentType.JSON).and().body("content.name", equalTo("User1"));
	}

}
