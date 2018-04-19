package com.att.demo.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "User",
description = "An User domain object")
public class Customer implements Serializable{
	
	private long id;
	
	private String name;

	

	public Customer(long id, String name) {
		super();
		this.id = id;
		this.name = name;
		
	}
	public Customer() {
		super();
	}
	
	@ApiModelProperty(
            access = "public",
            name = "id",
            required = true,
            value = "an identifier for the user")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@ApiModelProperty(
            access = "public",
            name = "name",
            required = true,
            value = "name of the Customer ")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	
	

	

}
