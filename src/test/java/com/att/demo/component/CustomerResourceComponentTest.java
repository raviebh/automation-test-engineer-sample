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
	
	

		
		// Test to verify creating customer --> conflict message
		
		@Test
		public void testCreateCustomer_conflict() {
			
				
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
		
		public String uri1="/customers/3?api_key=%2Fcustomers";
		
		
		// Test to get single Customer, lets assume customer with id 3 exists
		
		
		@Test
		public void testFindCustomer_success() {
			
				
			givenBaseSpec()
			.when()
			.get(uri1)
			.then()
				.statusCode(200);
		}
		
		// Test to get single account  --> not found, , lets assume customer with id 4 does not exists
		
		public String uri2="/customers/4?api_key=%2Fcustomers";
		
		@Test
		public void testFindCustomer_notFound() {
			
				
			givenBaseSpec()
			.when()
			.get(uri2)
			.then()
				.statusCode(404);
		}
		
	
}
