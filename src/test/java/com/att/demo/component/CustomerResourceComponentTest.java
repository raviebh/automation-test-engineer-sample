package com.att.demo.component;

import java.net.InetAddress;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.att.demo.model.User;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerResourceComponentTest {
	@LocalServerPort
	protected int randomServerPort;
	
	private String uri ="/users";
	
	@Before
	public void setUp() throws Exception {
		RestAssured.baseURI = "http://" + InetAddress.getLocalHost().getHostName() + ":" + randomServerPort + "/api";
		System.out.println(RestAssured.baseURI);
	}
	
	private  RequestSpecification givenBaseSpec() {
		return 
				RestAssured.given()
					.accept(ContentType.JSON)
					.contentType(ContentType.JSON);
	}
	
	@Test
	public void findUser(){
		givenBaseSpec()
			.get(uri+"/1/1")
			.then()
				.statusCode(200);
	}
	
	@Test
	public void testCreateUser_success() {
		
		User user = new User();
		user.setId(10);
		user.setName("Srini");
		user.setAge(32);
		user.setAccountId(10);
		givenBaseSpec()
			.body(user)
			.when()
				.post(uri)
				.then()
					.statusCode(201);
	}
	
}
