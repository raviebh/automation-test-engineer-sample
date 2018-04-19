package com.att.demo.component;

import static org.hamcrest.CoreMatchers.equalTo;

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

/**
 * @author katkah(Harish katkam)
 * Customer Component Test cases
 */

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerComponentTest {
	
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
	
	@Test
	/***
	 *  Create a customer test
	 */
	public void testCreateCustomer_success() {
		
		Customer customer = new Customer();
		customer.setId(1);
		customer.setName("Harish Katkam");
		customer.setFirstName("Harish");
		customer.setLastName("Katkam");
		
		givenBaseSpec()
			.body(customer)
			.when()
				.post(uri)
				.then()
					.statusCode(201);
	}
	
	

}
