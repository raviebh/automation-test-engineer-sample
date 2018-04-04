package com.att.demo.service;


import java.util.List;

import com.att.demo.model.Account;
import com.att.demo.model.User;

public interface UserService {
	
	User findById(long accountId, long userId);
	
	void saveUser(User user);
	
	List<User> findAllUsers();
	
	boolean isUserExistExist(User user);
	
}