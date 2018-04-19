package com.att.demo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.att.demo.model.Account;
import com.att.demo.model.Billing;



@Service("billingService")
public  class BillingServiceImpl implements BillingService{
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<Billing> billings;
	
	static{
		billings= populateDummyAccounts();
	}

	public List<Billing> findAllBillings() {
		return billings;
	}
	
	public Billing findByaccountNo(int accountNo) {
		for(Billing billing : billings){
			if(billing.getaccountNo() == accountNo){
				return billing;
			}
		}
		return null;
	}
	
	public Billing findByFirstName(String FirstName) {
		for(Billing billing : billings){
			if(billing.getFirstName().equalsIgnoreCase(FirstName)){
				return billing;
			}
		}
		return null;
	}
	
	public Billing findByLastName(String LastName) {
		for(Billing billing : billings){
			if(billing.getLastName().equalsIgnoreCase(LastName)){
				return billing;
			}
		}
		return null;
	}
	public void saveBilling(Billing billing) {
		counter.incrementAndGet();
		billings.add(billing);
	}

	public void updateBilling(Billing  billing) {
		int index = billings.indexOf(billing);
		billings.set(index, billing);
	}

	
	public boolean isBillingExist(Billing billing) {
		return findByFirstName(billing.getFirstName())!=null;
	}
	
	
	public boolean isBillingExist1(Billing billing) {
		return findByLastName(billing.getLastName())!=null;
	}
	
	private static List<Billing> populateDummyAccounts(){
		List<Billing> billings = new ArrayList<Billing>();
		billings.add(new Billing());
		billings.add(new Billing());
		billings.add(new Billing());
		return billings;
	}

	

	

	

}
