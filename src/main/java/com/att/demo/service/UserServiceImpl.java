package com.att.demo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.att.demo.model.Account;
import com.att.demo.model.User;

@Service("userService")
public class UserServiceImpl implements UserService {

	private static final AtomicLong counter = new AtomicLong();

	private static List<User> users;

	static {
		users = populateDummyUsers();

	}

	private static List<User> populateDummyUsers() {
		List<User> users = new ArrayList<User>();
		users.add(new User(counter.incrementAndGet(), "User1", 26, 1));
		users.add(new User(counter.incrementAndGet(), "User2", 27, 1));
		users.add(new User(counter.incrementAndGet(), "User3", 28, 1));
		return users;
	}

	@Override
	public User findById(long accountId, long userId) {
		for (User user : users) {
			if (user.getId() == userId) {
				return user;
			}
		}

		return null;
	}

	@Override
	public void saveUser(User user) {
		user.setId(counter.incrementAndGet());
		users.add(user);

	}

	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUserExistExist(User user) {
		return findById(user.getId(), user.getAccountId()) != null;
	}

}
