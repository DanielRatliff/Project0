package com.example.Services;

import java.sql.SQLException;
import java.util.List;

import com.example.dao.AccountDao;
import com.example.dao.ApplicationDao;
import com.example.dao.UserDao;
import com.example.exceptions.InvalidTransactionException;
import com.example.models.Account;
import com.example.models.Application;

public class CustomerServices {
	private int userId;
	private AccountDao acDao;
	private ApplicationDao apDao;
	
	public CustomerServices(int userId,AccountDao ac, ApplicationDao ap) {
		this.userId = userId;
		acDao = ac;
		apDao = ap;
	}
	public void withdrawl(int accId,double amount)throws InvalidTransactionException{
		if(amount <0.00) {
			throw new InvalidTransactionException();
		}
		Account a = acDao.getAccountById(accId);
		Double current = a.getBalance();
		if(current-amount <0) {
			throw new InvalidTransactionException();
		}else {
			current = current - amount;
			a.setBalance(current);
			acDao.updateAccount(a);
		}
	}
	public void deposit(int accId,double amount)throws InvalidTransactionException{
		if(amount < 0.00) {
			throw new InvalidTransactionException();
		}
		Account a = acDao.getAccountById(accId);
		Double current = a.getBalance();
		current = current + amount;
		a.setBalance(current);
		acDao.updateAccount(a);
		
	}
	public void apply(double start) {
		try {
		apDao.createApplication(userId, start);
		Application a = apDao.getApplicationByUserId(userId);
		System.out.println("Your application number: "+ a.getId() + " is currently pending. Check back later!");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void checkApplications() {
		Application a = apDao.getApplicationByUserId(userId);
		switch(a.getStatus().toLowerCase()) {
		case "approved":
			System.out.println("Your application was approved! An account has been created for you with a default pin(1234), check your acocunts and update your pin ASAP");
			apDao.removeApplication(a);
			break;
		case "denied" :
			System.out.println("Your appplication was Denied!Please try applying again or get in contact with you local branch for more info");
			apDao.removeApplication(a);
			break;
		case "pending":
			System.out.println("Your application is still pending, please check in at a later date.");
			break;
		default :
			System.out.println("Currently, you do not have any applications");
			break;
			
		}
		System.out.println("The status of your application number: "+a.getId()+" is currently "+a.getStatus());
		
	}
	public void changePin(int accId, int currentPin, int pin) {
		Account a = acDao.getAccountById(accId);
		if(a.getPin()==currentPin) {
			a.setPin(pin);
			acDao.updateAccount(a);
			System.out.println("PIN successfully changed");
		}else {
			System.out.println("Invalid PIN; could not process request try again");
		}
		
	}
	public List<Account> getUserAccounts() {
		List<Account> accountList = acDao.getUserAccounts(userId);
		return accountList;
	}
	 
}
