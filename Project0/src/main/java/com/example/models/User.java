package com.example.models;

import java.util.Random;

public class User {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private String password;
	
	public User() {
		super();
	}
	public User(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = firstName + lastName + (new Random().nextInt(9000)+1000);
		this.email = email;
		this.password = password;
	}
	
	public User(int id, String firstName, String lastName, String email, String username, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	@Override
	public String toString() {
		return "First Name: " + this.firstName + " Last Name: "+this.lastName + " Username: " + this.username;
	}
	public String getEmail() {
		return email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id=id;
	}
	public void setEmail(String email) {
		this.email= email;
	}
	public void setUsername(String username) {
		this.username=username;
	}

}
