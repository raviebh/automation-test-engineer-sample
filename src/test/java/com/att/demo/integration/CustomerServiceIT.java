package com.att.demo.integration;

import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class CustomerServiceIT {

		private String uri ="/customer";
		
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
		public void testfindAllCustomers_success() {
			
			givenBaseSpec()
					.when()
					.get(uri)
					.then()
						.statusCode(200);
	}

}
