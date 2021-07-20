package com.example.Services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.dao.AccountDao;
import com.example.dao.ApplicationDao;
import com.example.models.Account;
import com.example.models.Application;


public class EmployeeServices {
	private AccountDao acDao;
	private ApplicationDao apDao;
	
	public EmployeeServices(AccountDao ac, ApplicationDao ap) {
		acDao = ac;
		apDao = ap;
	}
	public void viewAccounts() {
		ArrayList<Account> accountList = new ArrayList<Account>();
		accountList = (ArrayList<Account>) acDao.getAllAccounts();
		for(Account a : accountList) {
			System.out.println(a);
		}
	}
	
	//public void viewTransactions()
	
	public void reviewApplications(Scanner in) {
		int input;
		List<Application> appList = new ArrayList<Application>();
		appList = apDao.getAllApplications();
		for(Application a : appList) {
			System.out.println(a);
			System.out.print("Enter 1 to approve, 2 to dissaprove and 3 to pass on to the next :");
			input = Integer.parseInt(in.nextLine());
			switch(input) {
			case 1: 
				a.setStatus("approved");
				apDao.updateApplication(a);
				Account acc = new Account(a.getStartBalance(),a.getUserId(),1234);
				try{
					acDao.createAccount(acc);
				}catch(SQLException e) {
					e.printStackTrace();
				}
				System.out.println("approved");
				break;
			case 2:
				a.setStatus("denied");
				apDao.updateApplication(a);
				System.out.println("denied");
				break;
			case 3:
				System.out.println("pending");
				break;
			default: 
				System.out.println("Invalid input, application was automatically passed");
				break;
			
			}
		}
	}
}

