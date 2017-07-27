package com.MSTS.TRMS_Project1.dao;

import java.sql.SQLException;
import java.util.List;

import com.MSTS.TRMS_Project1;

public class TRMS_EmployeeDAO {

	
	Employee getEmployee(int id) throws SQLException;
	
	int saveEmployee(Employee em) throws SQLException;
	
	void updateEmployee(Employee em) throws SQLException;
	
	List<Employee> getAllEmployees() throws SQLException;
	
	int deleteEmployee(int ... ids) throws SQLException;
	
}
