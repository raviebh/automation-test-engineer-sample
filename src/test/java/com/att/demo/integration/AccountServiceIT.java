package com.att.demo.integration;

import org.junit.Before;
import org.junit.Test;

import com.att.demo.model.Account;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;

import java.util.Collection;

import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.path.json.JsonPath;

import org.hamcrest.Matchers.*;

public class AccountServiceIT {
	
	private String uri ="/accounts";
	private Response response;
	private String accountid = "1";
		
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
	public void testTotalAccounts() {
	//response =	givenBaseSpec()
		//.when()
		//.get(uri)
	//	.then().extract().response();
		
	
		
		//String responseBodyString = response.getBody().asString();
		//JsonPath js= new JsonPath(responseBodyString);-->
		
		givenBaseSpec().
		    when().
		        get(uri).
		    then().
		        assertThat().
		        body("collection.size()", equalTo(3));
		
	
		
		
	}
	
	@Test
	public void testGetNameofFirstAccount() {
		
		  givenBaseSpec().
		    when().
		        get(uri).
		    then().
		        assertThat().
		        body("collection[0].name", equalTo("Account1"));
		
	}
	
	@Test
	public void testAddAccount() {
		
		  givenBaseSpec().contentType("application/json").
				  body(
		  		"{\r\n" + 
		  		"\"id\": 0,\r\n" + 
		  		"\"name\", \r\n" + 
		  		"\r\n" + 
		  		"}").when().post(uri).then().statusCode(200);
		
		}
	
	
	@Test
	public void testgetAccountbyAccountId() {
		
		 givenBaseSpec().
		 pathParam("accountid", accountid).
		    when().
		        get(uri).
		    then().
		        assertThat().
		        body("collection[0].name", equalTo("Account1"));
		
		}
	
	
	
	
}