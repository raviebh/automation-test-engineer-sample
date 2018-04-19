package com.att.demo.model;

import static org.junit.Assert.*;

import org.junit.Test;


import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


	

	@ApiModel(value = "Customer",
	description = "An Customer domain object")
	public class Customer implements Serializable {

		private long id;
		
		private String name;
		
		private String FirstName;
		
		private String LastName;
		
		/**
	     * Default Constructor 
	     * 
	     */
		public Customer() {
			super();
		}
		
		public Customer(long id, String FirstName, String LastName) {
			super();
			this.id = id;
			this.FirstName = FirstName;
			this.LastName = LastName;
		}
		
		@ApiModelProperty(
	            access = "public",
	            name = "id",
	            required = true,
	            value = "an identifier for the account (long)")
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
	            value = "account name (String)")
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
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
			Account other = (Account) obj;
			if (id != other.id)
				return false;
			return true;
		}

		/*@Override
		public String toString() {
			return "Account [id=" + id + ", name=" + name + "]";
		}
*/

	}



