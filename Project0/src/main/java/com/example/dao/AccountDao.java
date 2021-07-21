package com.example.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.example.models.Account;

public interface AccountDao {
	List<Account> getAllAccounts();
	void createAccount(Account a)throws SQLException;
	void updateAccount(Account a);
	List<Account> getUserAccounts(int userId);
	Account getAccountById(int id);
	void postTransfer(int acc1, double amount, int acc2);
	void approveTranser(int transferid);
	double getTransferAmount(int transferId);
	void deleteTransfer(int transferid);
	void getTransfersByAcc(int acc1);
	void getTransfersByAcc2(int acc2);
	
}
