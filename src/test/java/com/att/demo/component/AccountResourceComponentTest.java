package com.att.demo.component;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

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
import scala.annotation.strictfp;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountResourceComponentTest {
	@LocalServerPort
	protected int randomServerPort;

	private String uri ="/accounts";

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
	public void testfindAllAccount_success() {

		givenBaseSpec()
		  .when()
		   .get(uri)
		     .then()
		      .statusCode(200);
	}
	@Test
	public void testfindAllAccount_no_content() {

		givenBaseSpec()
		 .when()
		  .get(uri)
		   .then()
		    .statusCode(204);
	}
	@SuppressWarnings("deprecation")
	@Test
	public void testfindAccountResource_not_found() {
	
		Map<String,Long> info = new HashMap<>();
		info.put("accountId", 54321L);
		info.put("userId", 3005L);
		
		
		givenBaseSpec()
		.pathParameters(info)
		  .when()
		   .get(uri)
		  	.then()
		  	 .statusCode(404);
	}
	@SuppressWarnings("deprecation")
	@Test
	public void testfindAccountResource_success() {
		
		Map<String,Long> info = new HashMap<>();
		info.put("accountId", 54321L);
		info.put("userId", 3105L);

		givenBaseSpec()
		.pathParameters(info)
		  .when()
	  	  .get(uri)
		   .then()
		    .statusCode(200);
	}
	
	@Test()
	public void testCreateAccount_success() {


		Account account = new Account();
		account.setId(54321);
		account.setName("test-create");

		givenBaseSpec()
			.body(account)
			 .when()
			  .post(uri)
			   .then()
			    .statusCode(201);
	}
	@Test()
	public void testCreateAccount_conflict() {


		Account account = new Account();
		account.setId(54321);
		account.setName("test-create");

		givenBaseSpec()
		 .body(account)
		  .when()
		   .post(uri)
		    .then()
		     .statusCode(409);
	}
	
	
}
