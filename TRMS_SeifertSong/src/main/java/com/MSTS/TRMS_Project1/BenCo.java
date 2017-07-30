package com.MSTS.TRMS_Project1;

import java.sql.SQLException;

import com.MSTS.TRMS_Project1.dao.TRMS_EmployeeDAO;
import com.MSTS.TRMS_Project1.dao.TRMS_EmployeeDAOImpl;

public class BenCo extends Employee {
	
	public int autoApprove(Application app, int approvetimer)
	{
		//Overrides auto approval step, notifies DS of BenCo instead
		if(approvetimer>20)
		{
			//notify BenCo DS!!!
			return 1;
		}
		else
		{
			return 0;
		}
	}
	public void changeAward(Application app, int newVal)
	{
		//Can change amount awarded.
		//Must have a reason for awarding more than available
		//Notifies requester if amount is changed
		TRMS_EmployeeDAO dao = new TRMS_EmployeeDAOImpl();
		app.setAward(newVal);
		try {
			dao.updateApplication(app);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
