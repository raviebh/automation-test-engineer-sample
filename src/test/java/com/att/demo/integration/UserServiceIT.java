package com.att.demo.integration;

import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class UserServiceIT {
	private String uri ="/users";
	
	@Before
	public void setUp() throws Exception {
		String baseURI = System.getProperty("BASE_URL");
		RestAssured.baseURI = baseURI + "/api";
	}
	
	private RequestSpecification givenBaseSpec() {
		return 
				RestAssured.given()
					.accept(ContentType.JSON)
					.contentType(ContentType.JSON)
					;
	}
	
	@Test
	public void testfindUserResource_success() {
		
		 givenBaseSpec()
		  .pathParam("userId", 3105L)
				.when()
					.get(uri)
						.then()
							.statusCode(200);
	}
	
	@Test
	public void testfindUserResource_not_found() {
		
		 givenBaseSpec()
		  .pathParam("userId", 3005L)
				.when()
					.get(uri)
						.then()
							.statusCode(404);
	}
}
