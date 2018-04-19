package com.att.demo.integration;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.att.demo.model.Account;
import com.att.demo.model.User;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class AccountServiceIT {
	
	private String uri ="/users";
		
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
	public void testfindAllUsers_success() {
		
		givenBaseSpec()
				.when()
				.get(uri)
				.then()
					.statusCode(200);
	}
	
	@Test
	public void validatePost() {
		//for a new id, considering 4 as a new id
		User u1=new User();
		u1.setId(4);
		u1.setName("name1");
		u1.setAge(23);
		u1.setAccountId(1);
		
		givenBaseSpec().body(u1).when().post(uri+"/users").then().statusCode(201);
		//for a new id , but no account exists with that number
		User u2=new User();
		u2.setId(5);
		u2.setName("name2");
		u2.setAge(23);
		u2.setAccountId(20);
					
		givenBaseSpec().body(u2).when().post(uri+"/users").then().statusCode(404);
		
		//for an existing id
		User u3=new User();
		u3.setId(4);
		u3.setName("name1");
		u3.setAge(23);
		u3.setAccountId(2);
			
		givenBaseSpec().body(u3).when().post(uri+"/users").then().statusCode(409);
			
	}
	
	@Test
	public void getuserByAccountIdUserId()
	{
		//for a valid account id and userid -success
				int accountid=1; int userid=4;
				givenBaseSpec().when().get(uri+"/"+ accountid + "/" + userid).then().statusCode(200);
				JsonPath jsoneval= givenBaseSpec().when().get(uri+"/"+ accountid).jsonPath();
						
				//check that all keys have values.
				Assert.assertNotNull(jsoneval.get("content.id"));
				Assert.assertNotNull(jsoneval.get("content.name"));
				Assert.assertNotNull(jsoneval.get("content.age"));
				Assert.assertNotNull(jsoneval.get("content.accountId"));
				
				//for an invalid account id / userid -failure
				accountid=23;userid=4;
				givenBaseSpec().when().get(uri+"/"+ accountid + "/" + userid).then().statusCode(404);
				
				
	}
	}