package com.att.demo.component;

import java.net.InetAddress;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.att.demo.model.Account;
import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountResourceComponentTest {
	String id;
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
				.get("http://localhost:8080/api/accounts")
				.then()
					.statusCode(200)
					.extract().response().prettyPrint();
	}
	
	@Test
	public void testCreateAccount_success() {
		
			
		Account account = new Account();
		account.setId(54321);
		account.setName("test-create");
		
		givenBaseSpec()
			.body(account)
			.when()
				.post("http://localhost:8080/api/accounts")
				.then()
					.statusCode(201);
		               
		JsonPath path =new JsonPath(Str);
		  id=path.getString("id");
		 System.out.println(id);
		 		
	}
	
	
	
	@Test
	public void testGetAccount_success() {
		
		Account account = new Account();
		account.setId(12345);
		account.setName("test-get");
		
		givenBaseSpec()
			.body(account)
			.when()
				.post("http://localhost:8080/api/accounts")
				.then()
				.statusCode(201)
		 .extract().response().prettyPrint();

		givenBaseSpec()
		.when()
			.get("http://localhost:8080/api/accounts" + "/12345")
			.then()
				.statusCode(200)
		 .extract().response().prettyPrint();
	}
	@Test
	public void getbyid(){
		given()
		 .pathParam("getAccount",id)
		 
	   .contentType(ContentType.JSON)
	   .when().put("http://localhost:8080/index.html#!/account/{key}")
	   .then()
	   .body("name",is("Sweety"))
	   .extract().response().prettyPrint();
		
	}
	@Test
	public void getbyname(){
		given()
		 
	   .contentType(ContentType.JSON)
	   .when().put("http://localhost:8080/index.html#!/account/")
	   .then()
	   .body("Name",isequalto("Sweety"))
	   .extract().response().prettyPrint();
		
	}
	
		
		
	}
	
	
	

