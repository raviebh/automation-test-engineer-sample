package com.att.demo.integration;

import static org.hamcrest.CoreMatchers.is;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.att.demo.model.Billing;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;


public class BillingTest {
private String uri = "/Billings";

@BeforeClass
public static void init() {
	RestAssured.baseURI ="http://localhost:8080";
}

@Test(priority=0)
public void getbilling() {
	RestAssured.given()
	.contentType(ContentType.JSON)
	.when()
	.get(uri)
	.then()
	.statusCode(200)
	.body("", is(""));
	
}

@DataProvider(name="validBilling")
public Object[][] createData(){
	return new Object[][] {
		{ new  Billing(12,"sgshs","sgsh")
	},
};
}


@Test(priority=1,dataProvider="validBilling")
public void postBilling(Billing billing) {
	String id=given()
	.accept(ContentType.JSON)
	.contentType(ContentType.JSON)
	.body(billing)
	.when()
	.post(uri)
	.then()
	.statusCode(201)
	.body("accountNo",is(billing.getaccountNo))
	.body("FirstName",is(billing.getFirstName()))
	.body("LastName",is(billing.getLastName()))
	.extract()
	.path("id");
	
	RestAssured.given()
	.pathParam("id",id)
	.when()
	.get("/billings/{id}")
	.then()
	.statusCode(200)
	.body("accountNo",is(billing.getaccountNo()))
	.body("FirstName",is(billing.getFirstName()))
	.body("LastName",is(billing.getLastName()));
	}


@Test(priority=2)
public void validPostBilling() {
	Billing billing = new Billing(234,"shus","shsjd");
	String accountNo=given()
			.contentType(ContentType.JSON)
			.body(billing)
			.when()
			.post(uri)
			.then()
			.statuscode(200)
			.body("accountNo",is(billing.getaccountNo()))
			.body("FirstName",is(billing.getFirstName()))
			.body("LastName",is(billing.getLastName()))
			.extract()
			.path("id");
	given()
	.pathParam("id",id)
	.when()
	.get("/billings/{id}")
	.then()
	.statusCode(200)
	.body("accountNo",is(accountNo))
	.body("FirstName",is(billing.getFirstName()))
	.body("LastName",is(billing.getLastName()));
	
}


@Test(priority=3)
public void negativePostBilling() {
	Billing billing = new Billing(0,""," ");
	RestAssured.given()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.body(billing)
			.when()
			.post(uri)
			.then()
			.statusCode(400)
			.body("message", is("Fields cannot be empty"));
	
}


}

