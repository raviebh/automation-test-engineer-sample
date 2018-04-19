package com.att.demo.integration;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.att.demo.model.Account;
import com.att.demo.model.Customer;
import com.att.demo.model.User;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
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
	
	
	@Test
	public void validatePost() {
		//for a new id, considering 4 as a new id
		Customer c1=new Customer();
		c1.setId(4);
		c1.setFirstName("first");
		c1.setsecondName("last");
		
		givenBaseSpec().body(c1).when().post(uri+"/customers").then().statusCode(201);
		//for an existing id , but no account exists with that number
		Customer c2=new Customer();
		c2.setId(3);
		c2.setFirstName("first2");
		c2.setsecondName("last2");
					
		givenBaseSpec().body(c2).when().post(uri+"/customers").then().statusCode(404);
		
			
	}
	
	@Test
	public void getcustomerByCustomerId()
	{
		//for a valid customer id-success
				int customerid=1; 
				givenBaseSpec().when().get(uri+"/" + customerid).then().statusCode(200);
				JsonPath jsoneval= givenBaseSpec().when().get(uri+"/"+ customerid).jsonPath();
						
				//check that all keys have values.
				Assert.assertNotNull(jsoneval.get("content.id"));
				Assert.assertNotNull(jsoneval.get("content.firstName"));
				Assert.assertNotNull(jsoneval.get("content.lastName"));
			
				
				//for an invalid customer id -failure
				customerid=23;
				givenBaseSpec().when().get(uri+"/"+ customerid ).then().statusCode(404);
				
				
	}
	}