package com.example.models;

import java.util.Random;

public class Application {
	private int id;
	private String status;
	private int userId;
	private double startBalance;
	
	public Application() {
		super();
	}
	public Application(int id, String status, int userId, double startBalance) {
		super();
		this.id = id;
		this.status = status;
		this.userId = userId;
		this.startBalance = startBalance;
	}
	public Application(int userId, double startBalance) {
		super();
		this.userId=userId;
		this.startBalance = startBalance;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public double getStartBalance() {
		return startBalance;
	}
	public void setStartBalance(double startBalance) {
		this.startBalance = startBalance;
	}
	
		
	
}
