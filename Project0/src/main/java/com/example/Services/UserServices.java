package com.example.Services;

import com.example.models.User;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.example.dao.FileIO;
import com.example.exceptions.InvalidCredentialsException;
import com.example.exceptions.UserDoesNotExistexception;
import com.example.exceptions.UsernameAlreadyExistsException;

public class UserServices {
	private String file;
	private FileIO<User> io;
	
	public UserServices(String file) {
		this.file = file;
		this.io = new FileIO<User>(file);
	}
	
	public User signUp(String firstName, String lastName, String password) {
		ArrayList<User> users;
		
		try {
			users = io.readObject();
		}catch(FileNotFoundException e) {
			users = new ArrayList<User>();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		User u = new User(firstName,lastName,password);
		
		for(int i=0; i < users.size();i++) {
			if(users.get(i).getUsername().equals(u.getUsername())) {
				throw new UsernameAlreadyExistsException();
			}
		}
		users.add(u);
		io.writeObject(users);
		return u;
	}
	public User login(String username,String password) {
		ArrayList<User> users;
		
		try {
			users = io.readObject();
		}catch(FileNotFoundException e) {
			users = new ArrayList<User>();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		for(int i=0;i<users.size();i++) {
			if(users.get(i).getUsername().equals(username)) {
				if(users.get(i).getPassword().equals(password)) {
					System.out.println("Welcome!:)\n" + username + " was successfully logged in");
					return users.get(i);
				}
				throw new InvalidCredentialsException();
			}
		}
		throw new UserDoesNotExistexception();
	}
	public List<User> getAllUsers(){
		ArrayList<User> users;
		try {
			users=io.readObject();
		}catch(FileNotFoundException e) {
			users = new ArrayList<User>();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return users;
	}
	
}
