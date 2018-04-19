package com.att.demo.component;

import java.net.InetAddress;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.att.demo.model.Customer;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerResourceComponentTest {
	@LocalServerPort
	protected int randomServerPort;
	
	private String uri ="/customers";
	
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
	
	// Test for get all customers  
	
	@Test
	public void testfindAllCustomers_success() {
		
		givenBaseSpec()
				.when()
				.get(uri)
				.then()
					.statusCode(200);
	}
	
	// Test to verify creating customer
	
	@Test
	public void testCreateCustomer_success() {
		
			
		Customer customer = new Customer();
		customer.setId(54321);
		customer.setFirstName("test-create-first");
		customer.setLastName("test-create-last");
		
		givenBaseSpec()
			.body(customer)
			.when()
				.post(uri)
				.then()
					.statusCode(201);
	}
	
	
	// Test to verify creating account
	
		@Test
		public void testCreateAccount_success() {
			
				
			Customer customer = new Customer();
			customer.setId(54321);
			customer.setFirstName("test-create-first");
			customer.setLastName("test-create-last");
			
			givenBaseSpec()
				.body(customer)
				.when()
					.post(uri)
					.then()
						.statusCode(201);
		}
		
		// Test to verify creating account --> conflict message
		
		@Test
		public void testCreateAccount_conflict() {
			
				
			Customer customer = new Customer();
			customer.setId(1);
			customer.setFirstName("test-create-first");
			customer.setLastName("test-create-last");
			
			givenBaseSpec()
				.body(customer)
				.when()
					.post(uri)
					.then()
						.statusCode(409);
		}
		
		public String uri1="/customers/3?api_key=%2Faccounts";
		
		
		// Test to get single account
		
		
		@Test
		public void testFindAccount_success() {
			
				
			givenBaseSpec()
			.when()
			.get(uri1)
			.then()
				.statusCode(200);
		}
		
		// Test to get single account  --> not found
		
		public String uri2="/customers/4?api_key=%2Faccounts";
		
		@Test
		public void testFindAccount_notFound() {
			
				
			givenBaseSpec()
			.when()
			.get(uri2)
			.then()
				.statusCode(404);
		}
		
	
}
