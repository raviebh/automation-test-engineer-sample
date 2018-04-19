package com.att.demo.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Customer",
description = "An Customer domain object")
public class Customer implements Serializable {

	private long id;
	
	private String firstName;
	private String lastName;
	
	/**
     * Default Constructor 
     * 
     */
	public Customer() {
		super();
	}
	
	public Customer(long id, String firstName,String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	@ApiModelProperty(
            access = "public",
            name = "id",
            required = true,
            value = "an identifier for the Customer (long)")
	public long getId() {
		return id;
	}
	

	public void setId(long id) {
		this.id = id;
	}

	@ApiModelProperty(
            access = "public",
            name = "firstName",
            required = true,
            value = "Customer first name (String)")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	
	@ApiModelProperty(
            access = "public",
            name = "lastName",
            required = true,
            value = "Customer last name (String)")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", FirstName=" + firstName +", LastName="+lastName+ "]";
	}


}
