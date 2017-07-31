package com.MSTS.TRMS_Project1.servlet;

public class Person {
	private String userName;
	private String password;
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Person(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	@Override
	public String toString() {
		return "Person [userName=" + userName + ", password=" + password + "]";
	}	
	
}
