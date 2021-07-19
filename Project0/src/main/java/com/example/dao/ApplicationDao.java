package com.example.dao;

import java.sql.SQLException;
import java.util.List;

import com.example.models.Application;
import com.example.models.User;

public interface ApplicationDao {
	List<Application> getAllUsers();

	User getUserByUsername(String username);

	void createApplication(User u) throws SQLException;

	void updateApplication(User u);

}
