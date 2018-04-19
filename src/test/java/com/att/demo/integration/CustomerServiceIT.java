package com.att.demo.integration;


import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

/**
 * @author katkah(Harish katkam)
 * Customer Service Integration Test case
 */

public class CustomerServiceIT {

	private String uri = "/customers";

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
	public void testfindAllCustomers_success() {
		
		givenBaseSpec()
				.when()
				.get(uri)
				.then()
					.statusCode(200);
	}

}
