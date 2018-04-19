package com.att.demo.component;

import java.net.InetAddress;
import java.util.ArrayList;

import org.junit.Assert;
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
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

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
	public void testFindByvalid_accoutnid() {
		//for a valid account id
		int accountid=1;
		givenBaseSpec().when().get(uri+"/"+ accountid).then().statusCode(200);
		JsonPath jsoneval= givenBaseSpec().when().get(uri+"/"+ accountid).jsonPath();
				
		//check that all keys have values.
		Assert.assertNotNull(jsoneval.get("content.id"));
		Assert.assertNotNull(jsoneval.get("content.name"));
	
			
	}
	@Test
	public void testFindByinvalid_accoutnid() {
		//for an invalid account id, check 404 error.
		int accountid=4;
			//givenBaseSpec().when().get(uri+"/"+ accountid).then().statusCode(404);
		
		//test that there is no internal server error.
			int sc= RestAssured.get(uri+"/"+ accountid).statusCode();
			Assert.assertFalse(sc>499 && sc<=599);				
	}
	@Test
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
	public void testCreateAccountwithSameId_failure() {
		
		
		Account account = new Account();
		account.setId(1);
		account.setName("new-name");
		
		givenBaseSpec()
			.body(account)
			.when()
				.post(uri)
				.then()
					.statusCode(409);
	}
	
	@Test
	public void testCreateMultileAccounts()
	{
		ArrayList<Account> account = new ArrayList<>();
		account.add(new Account());
		account.add(new Account());
				
		account.get(0).setId(123);     
		account.get(0).setName("acc123");
		
		account.get(1).setId(456);     
		account.get(1).setName("acc456");
		
		int sc=RestAssured.given().accept(ContentType.JSON).body(account).when().post(uri).getStatusCode();
		//test that there is no internal server error.
		Assert.assertFalse(sc>499 && sc<=599);	
		System.out.println(sc);
	}
	
}
