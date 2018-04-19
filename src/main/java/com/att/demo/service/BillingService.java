package com.att.demo.service;


import java.util.List;

import com.att.demo.model.Account;
import com.att.demo.model.Billing;

public interface BillingService {
	
	Billing findByaccountNo(int accountNo);
	
	Billing findByFirstName(String FirstName);
	Billing findByLastName(String LastName);
	
	void saveBilling(Billing billing);
	
	List<Billing> findAllBillings();
	
	boolean isBillingExist(Billing billing);
	
}
