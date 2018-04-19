package com.att.demo.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Account",
description = "An Account domain object")
public class Billing implements Serializable {

	private long accountNo;
	
	private String FirstName;
	private String LastName;
	
	/**
     * Default Constructor 
     * 
     */
	public Billing() {
		super();
	}
	
	public Billing(long accountNo, String FirstName, String LastName) {
		super();
		this.accountNo = accountNo;
		this.FirstName = FirstName;
		this.LastName = LastName;
	}
	
	@ApiModelProperty(
            access = "public",
            name = "accountNo",
            required = true,
            value = "an identifier for the account (long)")
	public long getaccountNo() {
		return accountNo;
	}
	

	public void setaccountNo(long accountNo) {
		this.accountNo = accountNo;
	}

	@ApiModelProperty(
            access = "public",
            name = "FirstName",
            required = true,
            value = "account name (String)")
	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String FirstName) {
		this.FirstName = FirstName;
	}
	@ApiModelProperty(
            access = "public",
            name = "LirstName",
            required = true,
            value = "account name (String)")
	public String getLastName() {
		return LastName;
	}

	public void setLirstName(String LastName) {
		this.LastName = LastName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (accountNo ^ (accountNo >>> 32));
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
		Billing other = (Billing) obj;
		if (accountNo != other.accountNo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [accountNO=" + accountNo + ", firstname=" + FirstName+ " ,lastname=\" + LastName+ \"]";
	}


}
