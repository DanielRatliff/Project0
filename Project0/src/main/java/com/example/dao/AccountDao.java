package com.example.dao;

import java.sql.SQLException;
import java.util.List;

import com.example.models.Account;
import com.example.models.User;

public interface AccountDao {
	List<Account> getAllAccounts();
	void createAccount(Account a)throws SQLException;
	void updateAccount(Account a);
	List<Account> getUserAccounts(User u);
	
}