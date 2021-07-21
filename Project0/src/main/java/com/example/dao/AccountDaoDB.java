package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.models.Account;
import com.example.utils.ConnectionUtil;

public class AccountDaoDB implements AccountDao{
	ConnectionUtil conUtil = ConnectionUtil.getConnectionUtil();
	@Override
	public List<Account> getAllAccounts() {
		List<Account> accountList = new ArrayList<Account>();
		
		try {
			Connection con = conUtil.getConnection();
			String sql = "SELECT * FROM accounts";
			
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				//id,balance,customerid,pin
				accountList.add(new Account(rs.getInt(1),rs.getDouble(2),rs.getInt(3),rs.getInt(4)));
			}
			return accountList;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void createAccount(Account a) throws SQLException {
		Connection con = conUtil.getConnection();
		String sql = "INSERT INTO accounts(balance, customer_id, pin) values"
				+ "(?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setDouble(1, a.getBalance());
		ps.setInt(2, a.getuId());
		ps.setInt(3, a.getPin());
		
		ps.execute();
		
	}

	@Override
	public void updateAccount(Account a) {
		
		try {
			Connection con = conUtil.getConnection();
			String sql = "UPDATE accounts SET balance = ?, pin = ? "
					+ " WHERE accounts.account_id = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setDouble(1, a.getBalance());
			ps.setInt(2, a.getPin());
			ps.setInt(3, a.getId());
			
			ps.execute();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Account> getUserAccounts(int userId) {
		List<Account> accountList = new ArrayList<Account>();
		try {
			Connection con = conUtil.getConnection();
			String sql = "SELECT * FROM accounts WHERE customer_id = " + userId;
			
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				accountList.add(new Account(rs.getInt(1),rs.getDouble(2),rs.getInt(3),rs.getInt(4)));
			}
			return accountList;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public Account getAccountById(int id) {
		Account a = new Account();
		try {
			Connection con = conUtil.getConnection();
			String sql = "SELECT * FROM accounts WHERE account_id = " + id;
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			rs.next();
			a.setId(rs.getInt(1));
			a.setBalance(rs.getDouble(2));
			a.setuId(rs.getInt(3));
			a.setPin(rs.getInt(4));
			return a;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void postTransfer(int acc1, double amount, int acc2) {
		try {
			Connection con = conUtil.getConnection();
			String sql = "INSERT INTO transfer(acc1_id,acc2_id,amount) VALUES (?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, acc1);
			ps.setInt(2, acc2);
			ps.setDouble(3, amount);
			ps.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void approveTranser(int transferid) {
		try {
			Connection con = conUtil.getConnection();
			String sql = "UPDATE TABLE transfer SET status = 'approved' WHERE transfer_id = ? ";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, transferid);
			ps.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void getTransfersByAcc2(int acc2) {
		try {
			Connection con = conUtil.getConnection();
			String sql = "SELECT * FROM transfer WHERE acc2_id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, acc2);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println("Transfer id: " + rs.getInt(1) + " Transfering account: "+rs.getInt(2)+" Ammount: "+rs.getDouble(4));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void getTransfersByAcc(int acc1) {
		try {
			Connection con = conUtil.getConnection();
			String sql = "SELECT * FROM transfer WHERE acc1_id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, acc1);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println("Transfer id: " + rs.getInt(1) + " Receiving account: "+ rs.getInt(3) + " Ammount: "+ rs.getDouble(4));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void deleteTransfer(int transferid) {
		try {
			Connection con = conUtil.getConnection();
			String sql = "DELETE FROM transfer WHERE transfer_id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, transferid);
			ps.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public double getTransferAmount(int transferId) {
		try {
			Connection con = conUtil.getConnection();
			String sql = "SELECT * FROM transfer WHERE transfer_id = " + transferId;
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			rs.next();
			return rs.getDouble(4);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0.0;
	}
}
