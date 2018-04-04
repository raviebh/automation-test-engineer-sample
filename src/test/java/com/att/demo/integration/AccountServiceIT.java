package com.att.demo.integration;

import org.junit.Before;
import org.junit.Test;

import com.att.demo.model.Account;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class AccountServiceIT {
	
	private String uri ="/accounts";
	private String account1;
		
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
	public void testfindAllAccount_success() {
		
		givenBaseSpec()
				.when()
				.get(uri)
				.then()
					.statusCode(200);
	}
	
	@Test
	public void testCreateAccount_success() {
		
			
		Account account = new Account();
		account.setId(54321);
		account.setName("test-create");
		
		givenBaseSpec()
			.body(account)
			.when()
				.post(uri)
				.then()
					.statusCode(201);
	}
	
	@Test
	public void testCreateUser_failure() {
		givenBaseSpec()
		.body(account1)
		.when()
			.post(uri)
			.then()
				.statusCode(404);
		
	}
	
	@Test
	public void testGetAccount_success() {
		
		Account account = new Account();
		account.setId(12345);
		account.setName("test-get");
		
		givenBaseSpec()
			.body(account)
			.when()
				.post(uri)
				.then()
				.statusCode(201);

		givenBaseSpec()
		.when()
			.get(uri + "/12345")
			.then()
				.statusCode(200);
	}

}
