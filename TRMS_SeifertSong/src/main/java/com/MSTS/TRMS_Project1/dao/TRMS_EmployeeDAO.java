package com.MSTS.TRMS_Project1.dao;

import java.sql.SQLException;
import java.util.List;

import com.MSTS.TRMS_Project1.*;

public interface TRMS_EmployeeDAO {

	
	Employee getEmployee(String username) throws SQLException;
	int saveEmployee(Employee em) throws SQLException;
	void updateEmployee(Employee em) throws SQLException;
	
	
	Application getApplication(int id) throws SQLException;
	int saveApplication(Application ap) throws SQLException;
	void updateApplication(Application ap) throws SQLException;
	List<Application> getAllApplications() throws SQLException;
	int deleteApplication(int id) throws SQLException;
	
	
	Event getEvent(int id) throws SQLException;
	int saveEvent(Event ev) throws SQLException;
	
	List<Attachment> getAttachment(int id) throws SQLException;
	int saveAttachment(Attachment at) throws SQLException;
}
