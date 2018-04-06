package com.att.demo.integration;

import java.util.HashMap;
import java.util.Map;

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
	
	@SuppressWarnings("deprecation")
	@Test
	public void testfindAccountResource_success() {
		
		Map<String,Long> info = new HashMap<>();
		info.put("accountId", 54321L);
		info.put("userId", 3105L);

		givenBaseSpec()
			.pathParameters(info)
				.when()
					.get(uri)
						.then()
							.statusCode(200);
	}
}