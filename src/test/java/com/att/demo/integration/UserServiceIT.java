package com.att.demo.integration;

import org.junit.Before;
import org.junit.Test;

import com.att.demo.model.Account;

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

public class UserServiceIT {
	
	private String uri ="/users";
	private Response response;
	private String accountid = "1";
	private String userId = "1";
		
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
 		"\"name\": \"User1\"\r\n" + 
 		"\"age\": 10,\r\n" + 
 		"\"accountId\":\"2\"\r\n" + 
 		"}").when().post(uri).then().statusCode(200);
	}
	
	
	
	@Test
	public void testgetUserbyUserId() {
		
		 givenBaseSpec().
		 pathParam("accountId", accountid).pathParam("userId", userId).
		    when().
		        get("uri/{accountId}/{userId}").
		    then().
		       statusCode(200).and()
	            .body("user.name", containsString("User1"));
		
		}
	
	
	@Test
	public void testgetUserbyUserIdAsNullAsString() {
		
		 givenBaseSpec().
		 pathParam("accountId", accountid).pathParam("userId", "null").
		    when().
		        get("uri/{accountId}/{userId}").
		    then().
		       statusCode(200);
		
		}
	
	
	
}