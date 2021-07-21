package com.example.dao;

import java.sql.SQLException;
import java.util.List;

import com.example.models.Application;

public interface ApplicationDao {
	List<Application> getAllApplications();

	Application getApplicationByUserId(int userId);

	void createApplication(int userId,double startBalance) throws SQLException;

	void updateApplication(Application a);
	
	void removeApplication(Application a);
	

}
