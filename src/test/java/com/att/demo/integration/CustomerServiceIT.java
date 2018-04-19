package com.att.demo.integration;

import org.junit.Before;
import org.junit.Test;

import com.att.demo.model.Customer;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

import java.util.Collection;

import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.path.json.JsonPath;

import org.hamcrest.Matchers.*;

public class CustomerServiceIT {
	
	private String uri ="/customers";
	private Response response;
	private String customerId = "1";
	private String customerName = "Customer6";
		
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
	public void testCreateUser() {
		
		 givenBaseSpec().
		  body(
 		"{\r\n" + 
 		"\"id\":6,\r\n" + 
 		"\"name\": \"Customer6\"\r\n" + 
 		 		"}").when().post(uri).then().statusCode(200);
	}
	
	
	
	@Test
	public void testgetCustomerbyCustomerId() {
		
		 givenBaseSpec().
		 pathParam("customerId", customerId).
		    when().
		        get("uri/{customerId}").
		    then().
		       statusCode(200).and()
	            .body("customer.name", containsString("Customer6"));
		
		}
	
	@Test
	public void testgetCustomerbyCustomerName() {
		
		 givenBaseSpec().
		 pathParam("customerName", customerName).
		    when().
		        get(uri).
		    then().
		       statusCode(200).and()
	            .body("customer.name", containsString("Customer6"));
		
		}
	
	
	
	
}