package com.example.models;

public class Account {
	private String usernmame;
	private String accountPass;
	private int accountNumber;
	private double balance;
	public Account() {
		super();
	}
	public Account(String usernmame, String accountPass, double balance) {
		super();
		this.usernmame = usernmame;
		this.accountPass = accountPass;
		this.balance = balance;
	}
	public String getAccountPass() {
		return accountPass;
	}
	public void setAccountPass(String accountPass) {
		this.accountPass = accountPass;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getUsernmame() {
		return usernmame;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	
}
