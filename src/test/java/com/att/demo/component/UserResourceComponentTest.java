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

import com.att.demo.model.User;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

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
	public void testCreateUser_Success() {
		User user = new User(3105L, "Demo User", 32, 54321 );
		givenBaseSpec()
		  .body(user)
		    .when()
		      .post(uri)
		        .then()
		          .statusCode(201);

	}
	@Test
	public void testCreateUser_conflict() {
		User user = new User(3105L, "Demo User", 32, 54321 );
		
		givenBaseSpec()
		  .body(user)
		    .when()
		      .post(uri)
		        .then()
		          .statusCode(201);
		
		givenBaseSpec()
			.body(user)
				.when()
					.post(uri)
						.then()
							.statusCode(409);

	}

	@Test
	public void testfindUserResources_success()
	{
		  givenBaseSpec()
		  .pathParam("userId", 3105L)
				.when()
					.get(uri)
						.then()
							.statusCode(200);
	}
	
	
	

}
