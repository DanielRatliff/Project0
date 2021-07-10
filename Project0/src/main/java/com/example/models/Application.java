package com.example.models;

import java.util.Random;

public class Application {
	private String username;
	private double startBalance;
	private boolean approved = false;
	private int applicationID;
	public Application() {
		super();
	}
	public Application(String username, double startBalance) {
		super();
		this.username = username;
		this.startBalance = startBalance;
		this.applicationID = (new Random().nextInt(9000)+1000);
	}
	public int getApplicationID() {
		return applicationID;
	}
	public void setApplicationID(int applicationID) {
		this.applicationID = applicationID;
	}
	public String getUsername() {
		return username;
	}
	public double getStartBalance() {
		return startBalance;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	@Override
	public String toString() {
		return "Username: " + username + "Starting balance: " + startBalance + "\n Current Status: " + approved;
	}
	
	
}
