package com.example.Services;

import com.example.models.User;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.dao.FileIO;
import com.example.dao.UserDao;
import com.example.dao.UserDaoDB;
import com.example.exceptions.InvalidCredentialsException;
import com.example.exceptions.UserDoesNotExistexception;
import com.example.exceptions.UsernameAlreadyExistsException;

public class UserServices {
	private UserDao uDao;
	
	public UserServices(UserDao u) {
		this.uDao = u;
		
	}
	
	public User signUp(String first, String last, String email, String password)throws InvalidCredentialsException{
		User u = new User(first,last,email,password);
		
		try {
			uDao.createUser(u);
		}catch(SQLException e) {
			throw new InvalidCredentialsException();
		}
		return u;
	}
	
	public User signIn(String username, String password)throws InvalidCredentialsException{
		
		User u = uDao.getUserByUsername(username);
		
		if(u.getId()==0) {
			throw new InvalidCredentialsException();
		}else if (!u.getPassword().equals(password)) {
			throw new InvalidCredentialsException();
		}else {
			return u;
		}
	}
	
}
