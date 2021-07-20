package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.models.Application;
import com.example.models.User;
import com.example.utils.ConnectionUtil;

public class ApplicationDaoDB implements ApplicationDao{
	ConnectionUtil conUtil = ConnectionUtil.getConnectionUtil();
	@Override
	public List<Application> getAllApplications() {
		List<Application> applicationList = new ArrayList<Application>();
		try {
			Connection con = conUtil.getConnection();
			String sql = "SELECT * FROM application";
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()) {
				applicationList.add(new Application(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getDouble(4)));
			}
			return applicationList;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Application getApplicationByUserId(int userId) {
		Application a = new Application();
		try {
		Connection con = conUtil.getConnection();
		String sql = "SELECT * FROM application WHERE customer_id = "+ userId;
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(sql);
		a.setId(rs.getInt(1));
		a.setStatus(rs.getString(2));
		a.setUserId(rs.getInt(3));
		a.setStartBalance(rs.getDouble(4));
		
		return a;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void createApplication(int userId, double startBalance) throws SQLException {
		try {
		Connection con = conUtil.getConnection();
		String sql = "INSERT INTO application(customer_id,balance) VALUES (?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1, userId);
		ps.setDouble(2, startBalance);
		
		ps.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateApplication(Application a) {
		try {
			Connection con = conUtil.getConnection();
			String sql = "UPDATE application SET status = ? WHERE customer_id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, a.getStatus());
			ps.setInt(2, a.getUserId());
			ps.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void removeApplication(Application a) {
		try {
			Connection con = conUtil.getConnection();
			String sql = "DELETE FROM application WHERE account_id = "+a.getId();
			Statement s = con.createStatement();
			s.executeQuery(sql);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
