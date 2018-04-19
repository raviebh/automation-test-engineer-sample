/**
 * 
 */
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

import com.att.demo.model.User;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

/**
 * @author katkah(Harish katkam)
 * User resource component test cases.
 *
 */

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserResourceComponentTest {
	
	@LocalServerPort
	protected int randomServerPort;
	
	private String uri ="/users";
	
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
	 *  Create user
	 */
	public void testCreateUser_success() {
		
		User user = new User();
		user.setId(1);
		user.setName("Account10");
		user.setAge(10);
		user.setAccountId(0);
		
		givenBaseSpec()
			.body(user)
			.when()
				.post(uri)
				.then()
					.statusCode(201);
	}
	
	

}
