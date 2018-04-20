package com.att.demo.component;

import java.net.InetAddress;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.att.demo.model.Account;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;


public class CustomerResourceTest {

		
		protected int randomServerPort;
		
		private String uri ="/Customer";
		
		@Before
		public void setUp() throws Exception {
			RestAssured.baseURI = "http://" + InetAddress.getLocalHost().getHostName() + ":" + randomServerPort + "/api";
		}
		
		private  RequestSpecification givenBaseSpec() {
			return 
					RestAssured.given()
						.accept(ContentType.JSON)
						.contentType(ContentType.JSON);
		}
		
		@Test
		public void testfindAllCustomer_success() {
			
			givenBaseSpec()
					.when()
					.get(uri)
					.then()
						.statusCode(200);
		}
		
		@Test
		public void testCreateCustomer_success() {
			
				
			Account account = new Account();
			account.setId(54321);
			account.setFirstName("test-create");
			account.setLastName("test2-create");
			
			givenBaseSpec()
				.body(account)
				.when()
					.post(uri)
					.then()
						.statusCode(201);
		}
	





	}


