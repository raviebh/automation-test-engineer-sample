package com.att.demo.integration;

import org.junit.Before;
import org.junit.Test;

import com.att.demo.model.Account;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class AccountServiceIT {
	
	private String uri ="/accounts";
		
	@Before
	public void setUp() throws Exception {
		String baseURI = System.getProperty("http://localhost:8080/index.html#");
		RestAssured.baseURI = baseURI + "/default";
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
		account.setId(3456);
		account.setName("Karth");
		
		givenBaseSpec()
			.body(account)
			.when()
				.post(uri)
				.then()
					.statusCode(201);
	}
	
	@Test
	public void testCreateUser_failure() {
		//TO-DO	
	}
	
	@Test
	public void testGetAccount_success() {
		
		Account account = new Account();
		account.setId(3456);
		account.setName("Karth");
		
		givenBaseSpec()
			.body(account)
			.when()
				.post(uri)
				.then()
				.statusCode(201);

		givenBaseSpec()
		.when()
			.get(uri + "/3456")
			.then()
				.statusCode(200);
	}

}
