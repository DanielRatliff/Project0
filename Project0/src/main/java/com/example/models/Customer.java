package com.example.models;

import java.util.List;
import java.util.ArrayList;

public class Customer extends User{
	private List<Account> accts;

	public Customer() {
		super();
	}

	public Customer(String firstName, String lastName, String password) {
		super(firstName, lastName, password);
		accts = new ArrayList<Account>();
	}
	
	
}
