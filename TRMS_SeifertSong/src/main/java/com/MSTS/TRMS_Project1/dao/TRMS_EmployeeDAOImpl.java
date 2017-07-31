package com.MSTS.TRMS_Project1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.MSTS.TRMS_Project1.*;
import com.MSTS.util.ConnectionFactory;

public class TRMS_EmployeeDAOImpl implements TRMS_EmployeeDAO {


	Connection conn = null;
	
	public void setup() throws SQLException{
		
		conn = ConnectionFactory.getInstance().getConnection();
	}

	public TRMS_EmployeeDAOImpl()
	{
		try {
			setup();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Employee getEmployee(String username) throws SQLException{
		
		Statement stmt;
		ResultSet rs = null;
		int nullflag = 0;
		String sql = "SELECT * FROM TRMS_EMPLOYEES WHERE USERNAME='"+username.toUpperCase() + "'";
		Employee em = new Employee();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				em.setE_ID(rs.getInt(1));
				em.setEmployeeUsername(rs.getString(2));
				em.setDepartment(rs.getString(3));
				em.setDS_ID(rs.getInt(4));
				em.setDH_ID(rs.getInt(5));
				nullflag = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		em.setPassword(this.getPassword(username.toUpperCase()));
		if(nullflag==0)
		{
			em.setEmployeeUsername("ERROR");
		}
		return em;
		
	}
	
	public String getPassword(String username)
	{
		Statement stmt;
		ResultSet rs = null;
		String pw = null;
		
		String sql = "SELECT * FROM TRMS_PASSWORDS";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){

				if(username.equals(rs.getString("USERNAME")))
					pw = rs.getString(2);
				//System.out.println(pw);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if("".equals(pw))
			return "Error";
		else
			return pw;
		
	}

	@Override
	public void updateEmployee(Employee em) throws SQLException{
		conn.setAutoCommit(false);
		String sql = "UPDATE TRMS_EMPLOYEES SET E_ID=?, USERNAME=?, DEPARTMENT=?, DS_ID=?, DH_ID=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, em.getE_ID());
		pstmt.setString(2, em.getEmployeeUsername());
		pstmt.setString(3, em.getDepartment());
		pstmt.setInt(4, em.getDS_ID());
		pstmt.setInt(5, em.getDH_ID());

		pstmt.executeUpdate();

		conn.commit();
		
		conn.setAutoCommit(true);
		
	}


	@Override
	public int saveEmployee(Employee em) throws SQLException {
		conn.setAutoCommit(false);
		String sql = "INSERT INTO TRMS_EMPLOYEES (E_ID, USERNAME, DEPARTMENT, DS_ID, DH_ID)"
				+ "VALUES(?, ?, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, em.getE_ID());
		pstmt.setString(2, em.getEmployeeUsername());
		pstmt.setString(3, em.getDepartment());
		pstmt.setInt(4, em.getDS_ID());
		pstmt.setInt(5, em.getDH_ID());

		pstmt.executeUpdate();
		
		conn.commit();
		
		conn.setAutoCommit(true);
		return 0;
	}

	@Override
	public Application getApplication(int id) throws SQLException {
		Statement stmt;
		ResultSet rs = null;
		
		//Load from the database
		Application app = new Application();
		conn = ConnectionFactory.getInstance().getConnection();
		String sql;
		sql = "SELECT * FROM TRMS_APPLICATIONS LEFT JOIN TRMS_EMPLOYEES ON "
				+ "TRMS_APPLICATIONS.E_ID = TRMS_EMPLOYEES.E_ID WHERE APP_ID='" + id + "'";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				app.setApp_ID(rs.getInt(1));
				app.setE_ID(rs.getInt(2));
				app.setBC_ID(rs.getInt(3));
				app.setDepartment(rs.getString(4));
				app.setDS_approval(rs.getInt(5));
				app.setDH_approval(rs.getInt(6));
				app.setBenCo_approval(rs.getInt(7));
				app.setEvent_ID(rs.getInt(8));
				app.setInfoReqID(rs.getInt(9));
				app.setInfoReq(rs.getString(10));
				app.setStatus(rs.getString(11));
				app.setGrade(rs.getString(12));
				app.setAward(rs.getInt(13));
				app.setDS_ID(rs.getInt(17));
				app.setDH_ID(rs.getInt(18));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return app;
	}

	@Override
	public int saveApplication(Application ap) throws SQLException {
		conn.setAutoCommit(false);
		String sql = "INSERT INTO TRMS_APPLICATIONS(APP_ID, E_ID, BC_ID, DEPARTMENT, DS_APPROVAL,"
				+ "DH_APPROVAL, BENCO_APPROVAL, EVENT_ID, RESPONSE_REQ_ID, RESPONSE_REQ, STATUS, FINAL_GRADE, AWARD)"
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, ap.getApp_ID());
		pstmt.setInt(2, ap.getE_ID());
		pstmt.setInt(3, ap.getBC_ID());
		pstmt.setString(4, ap.getDepartment());
		pstmt.setInt(5, ap.getDS_approval());
		pstmt.setInt(6, ap.getDH_approval());
		pstmt.setInt(7, ap.getBenCo_approval());
		pstmt.setInt(8, ap.getEvent_ID());
		pstmt.setInt(9, ap.getInfoReqID());
		pstmt.setString(10, ap.getInfoReq());
		pstmt.setString(11, ap.getStatus());
		pstmt.setString(12, ap.getGrade());
		pstmt.setInt(13, ap.getAward());

		pstmt.executeUpdate();

		conn.commit();
		
		conn.setAutoCommit(true);
		
		return 0;
	}

	@Override
	public void updateApplication(Application ap) throws SQLException {
		conn.setAutoCommit(false);
		String sql = "UPDATE TRMS_APPLICATIONS SET APP_ID=?, E_ID=?, BC_ID=?, DEPARTMENT=?,"
				+ "DS_APPROVAL=?, DH_APPROVAL=?, BENCO_APPROVAL=?, EVENT_ID=?, "
				+ "RESPONSE_REQ_ID=?, RESPONSE_REQ=?, STATUS=?, FINAL_GRADE=?, AWARD=? WHERE APP_ID=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, ap.getApp_ID());
		pstmt.setInt(2, ap.getE_ID());
		pstmt.setInt(3, ap.getBC_ID());
		pstmt.setString(4, ap.getDepartment());
		pstmt.setInt(5, ap.getDS_approval());
		pstmt.setInt(6, ap.getDH_approval());
		pstmt.setInt(7, ap.getBenCo_approval());
		pstmt.setInt(8, ap.getEvent_ID());
		pstmt.setInt(9, ap.getInfoReqID());
		pstmt.setString(10, ap.getInfoReq());
		pstmt.setString(11, ap.getStatus());
		pstmt.setString(12, ap.getGrade());
		pstmt.setInt(13, ap.getAward());
		pstmt.setInt(14, ap.getApp_ID());

		pstmt.executeUpdate();
		
		conn.commit();
		
		conn.setAutoCommit(true);
		
		
	}

	@Override
	public ArrayList<Application> getAllApplications() throws SQLException {
		Statement stmt;
		ResultSet rs = null;
		
		//Load from the database
		Application app = new Application();
		ArrayList<Application> al = new ArrayList<Application>();
		conn = ConnectionFactory.getInstance().getConnection();
		String sql;
		sql = "SELECT * FROM TRMS_APPLICATIONS LEFT JOIN TRMS_EMPLOYEES ON "
				+ "TRMS_APPLICATIONS.E_ID = TRMS_EMPLOYEES.E_ID";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				app.setApp_ID(rs.getInt(1));
				app.setE_ID(rs.getInt(2));
				app.setBC_ID(rs.getInt(3));
				app.setDepartment(rs.getString(4));
				app.setDS_approval(rs.getInt(5));
				app.setDH_approval(rs.getInt(6));
				app.setBenCo_approval(rs.getInt(7));
				app.setEvent_ID(rs.getInt(8));
				app.setInfoReqID(rs.getInt(9));
				app.setInfoReq(rs.getString(10));
				app.setStatus(rs.getString(11));
				app.setGrade(rs.getString(12));
				app.setAward(rs.getInt(13));
				app.setDS_ID(rs.getInt(17));
				app.setDH_ID(rs.getInt(18));
				al.add(app);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return al;
	}

	@Override
	public int deleteApplication(int id) throws SQLException {
		conn.setAutoCommit(false);
		String sql = "UPDATE TRMS_APPLICATIONS SET STATUS=? WHERE APP_ID=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, "inactive");
		pstmt.setInt(2, id);

		pstmt.executeUpdate();
		
		conn.commit();
		
		conn.setAutoCommit(true);
		
		return 0;
	}
	public int remakeApplication(int id) throws SQLException {
		conn.setAutoCommit(false);
		String sql = "UPDATE TRMS_APPLICATIONS SET STATUS=? WHERE APP_ID=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, "active");
		pstmt.setInt(2, id);

		pstmt.executeUpdate();
		
		conn.commit();
		
		conn.setAutoCommit(true);
		
		return 0;
	}

	@Override
	public Event getEvent(int id) throws SQLException {
		Statement stmt;
		ResultSet rs = null;
		
		//Load from the database
		Event ev = new Event();
		conn = ConnectionFactory.getInstance().getConnection();
		String sql;
		sql = "SELECT * FROM TRMS_EVENTS WHERE EVENT_ID='" + id + "'";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				ev.setEvent_ID(rs.getInt(1));
				ev.setDate(rs.getString(2));
				ev.setLocation(rs.getString(3));
				ev.setDescription(rs.getString(4));
				ev.setCost(rs.getDouble(5));
				ev.setGrading_Format(rs.getString(6));
				ev.setPassing_Grade(rs.getString(7));
				ev.setEvent_Type(rs.getString(8));
				ev.setJustification(rs.getString(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ev;
	}

	@Override
	public int saveEvent(Event ev) throws SQLException {
		conn.setAutoCommit(false);
		String sql = "INSERT INTO TRMS_EVENTS(EVENT_ID, EVT_DATETIME, EVT_LOCATION, EVT_DESCRIPTION, EVT_COST,"
				+ "GRADING_FORMAT, PASSING_GRADE, EVENT_TYPE, JUSTIFICATION)"
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, ev.getEvent_ID());
		pstmt.setString(2, ev.getDate());
		pstmt.setString(3, ev.getLocation());
		pstmt.setString(4, ev.getDescription());
		pstmt.setDouble(5, ev.getCost());
		pstmt.setString(6, ev.getGrading_Format());
		pstmt.setString(7, ev.getPassing_Grade());
		pstmt.setString(8, ev.getEvent_Type());
		pstmt.setString(9, ev.getJustification());

		pstmt.executeUpdate();
		
		
		conn.commit();
		
		conn.setAutoCommit(true);
		return 0;
	}

	@Override
	public List<Attachment> getAttachment(int id) throws SQLException {
		Statement stmt;
		ResultSet rs = null;
		
		//Load from the database
		ArrayList<Attachment> al = new ArrayList<Attachment>();
		Attachment at = new Attachment();
		conn = ConnectionFactory.getInstance().getConnection();
		String sql;
		sql = "SELECT * FROM TRMS_ATTACHMENTS WHERE APP_ID='" + id + "'";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				at.setApp_ID(rs.getInt(1));
				at.setDoc_Name(rs.getString(2));
				at.setDescription(rs.getString(3));
				al.add(at);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return al;
	}

	@Override
	public int saveAttachment(Attachment at) throws SQLException {
		conn.setAutoCommit(false);
		String sql = "INSERT INTO TRMS_ATTACHMENTS(APP_ID, DOC_NAME, DESCRIPTION)"
				+ "VALUES(?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, at.getApp_ID());
		pstmt.setString(2, at.getDoc_Name());
		pstmt.setString(3, at.getDescription());

		pstmt.executeUpdate();
		
		conn.commit();
		
		conn.setAutoCommit(true);
		return 0;
	}

	
}
