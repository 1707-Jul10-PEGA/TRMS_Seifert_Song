package com.MSTS.TRMS_Project1;

import java.sql.SQLException;
import java.util.ArrayList;

import com.MSTS.TRMS_Project1.dao.TRMS_EmployeeDAO;
import com.MSTS.TRMS_Project1.dao.TRMS_EmployeeDAOImpl;

public class Employee {
	int E_ID;
	String EmployeeUsername;
	String Department;
	int DS_ID;
	int DH_ID;
	String password;
	
	
	
	public void approve(int id)
	{
		//Approves an application
		TRMS_EmployeeDAO dao = new TRMS_EmployeeDAOImpl();
		Application approved;
		try {
			approved = dao.getApplication(id/*PROVIDED APP ID*/);
		
		if(this.E_ID == approved.BC_ID)
		{
			approved.BenCo_approval = 1;
			System.out.println("You have approved this as a BenCo");
		}
		if(this.E_ID == approved.DH_ID)
		{
			approved.DH_approval = 1;
			System.out.println("You have approved this as a DH");
		}
		if(this.E_ID == approved.DS_ID)
		{
			approved.DS_approval = 1;
			System.out.println("You have approved this as a DS");
		}
		dao.updateApplication(approved);
		//Log application approved
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void reject(int id, String reason)
	{
		//Rejects an application
		TRMS_EmployeeDAO dao = new TRMS_EmployeeDAOImpl();
		Application rejected;
		try {
			rejected = dao.getApplication(id/*PROVIDED APP ID*/);
		
		if(this.E_ID == rejected.BC_ID)
		{
			rejected.BenCo_approval = 2;
			System.out.println("You have rejected this as a BenCo");
		}
		if(this.E_ID == rejected.DH_ID)
		{
			rejected.DH_approval = 2;
			System.out.println("You have rejected this as a DH");
		}
		if(this.E_ID == rejected.DS_ID)
		{
			rejected.DS_approval = 2;
			System.out.println("You have rejected this as a DS");
			System.out.println("Your supplied reason is: ");
			System.out.println(reason);
		}
		dao.updateApplication(rejected);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void clarify(int Appid, int reqID, String reqInfo)
	{
		//Asks for more info.
		//Should return a NAME (desired person) and DESCRIPTION (desired info)
		TRMS_EmployeeDAO dao = new TRMS_EmployeeDAOImpl();
		Application clarify;
		try {
			clarify = dao.getApplication(Appid/*PROVIDED APP ID*/);
		
		clarify.InfoReqID = reqID;//Requested employee here
		clarify.InfoReq = reqInfo; //Requested info here
		dao.updateApplication(clarify);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void cancel(int id)
	{
		//Cancel an application they filed
		TRMS_EmployeeDAO dao = new TRMS_EmployeeDAOImpl();
		Application delete;
		try {
			delete = dao.getApplication(id/*PROVIDED APP ID*/);
		
		if(this.E_ID == delete.E_ID)
		{
			int x = delete.App_ID;
			System.out.println("Cancelled request application");
			dao.deleteApplication(x);
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void apply(Application newApp)
	{
		//Apply for a Tuition Refund
		TRMS_EmployeeDAO dao = new TRMS_EmployeeDAOImpl();
		try {
			dao.saveApplication(newApp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void attachDoc(Attachment att)
	{
		//Attaches a document to an application
		TRMS_EmployeeDAO dao = new TRMS_EmployeeDAOImpl();
		try {
			dao.saveAttachment(att);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void uploadFinal(Application app, String grade)
	{
		//Upload a final grade/presentation for any interested party to see
		//given Application(should already be accessed) and a grade
		TRMS_EmployeeDAO dao = new TRMS_EmployeeDAOImpl();
		app.setGrade(grade);
		try {
			dao.updateApplication(app);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void viewStatus(int App_ID)
	{
		//Views the status of an application, given application id
		TRMS_EmployeeDAO dao = new TRMS_EmployeeDAOImpl();
		try {
			dao.getApplication(App_ID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public int autoApprove(Application app, int approvetimer)
	{
		//Automatically approves a request after a set amount of time
		if(approvetimer>20)
		{
			//Automatically approve
			this.approve(app.getApp_ID());
			return 1;
		}
		else
		{
			return 0;
		}
	}
	public Employee login(String username, String pw)
	{
		//Login to a user account
		TRMS_EmployeeDAO dao = new TRMS_EmployeeDAOImpl();
		Employee em = new Employee();
		try {
			em = dao.getEmployee(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(em.EmployeeUsername == username)
		{
			//Username matched!!!
			if(em.password == pw)
			{
				//Password matched!!!
				//Allowed to access functions
				return em;
			}
			else
			{
				//Password did not match!
				return null;
			}
		}
		else
		{
			//User does not exist!
			return null;
		}
		
	}
	
	public ArrayList<Application> getAssociatedApplications(Employee em)
	{
		ArrayList<Application> al = new ArrayList<Application>();
		Application app = new Application();
		TRMS_EmployeeDAO dao = new TRMS_EmployeeDAOImpl();
		try {
			al = (ArrayList<Application>) dao.getAllApplications();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < al.size(); i++)
		{
			if("".equals(al.get(i)))
			{
				//Application is null, remove from returned list
				al.remove(i);
			}
			else
			{
				app = al.get(i);
				if((em.E_ID==app.E_ID) || (em.E_ID==app.BC_ID) || (em.E_ID==app.DS_ID) || (em.E_ID==app.DH_ID))
				{
					//Application is associated to this Employee ID!
					//Don't touch it!
				}
				else
				{
					//Application is NOT associated to this Employee ID!
					al.remove(i);
				}
			}
		}
		return al;
	}
	
	public void logout()
	{
		//Logout of a user account
	}
	public void approvePresentation(Application app, String approval)
	{
		//Usable only by DS to confirm presentation was adequate
		TRMS_EmployeeDAO dao = new TRMS_EmployeeDAOImpl();
		app.setStatus(approval);
		try {
			dao.updateApplication(app);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public int getE_ID() {
		return E_ID;
	}
	public void setE_ID(int e_ID) {
		E_ID = e_ID;
	}
	public String getEmployeeUsername() {
		return EmployeeUsername;
	}
	public void setEmployeeUsername(String employeeUsername) {
		EmployeeUsername = employeeUsername;
	}
	public String getDepartment() {
		return Department;
	}
	public void setDepartment(String department) {
		Department = department;
	}
	public int getDS_ID() {
		return DS_ID;
	}
	public void setDS_ID(int dS_ID) {
		DS_ID = dS_ID;
	}
	public int getDH_ID() {
		return DH_ID;
	}
	public void setDH_ID(int dH_ID) {
		DH_ID = dH_ID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
