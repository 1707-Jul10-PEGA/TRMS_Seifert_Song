package com.MSTS.TRMS_Project1;

public class Event {
	int Event_ID;
	String Date;
	String Location;
	String Description;
	Double Cost;
	String Grading_Format;
	String Passing_Grade;
	String Event_Type;
	String Justification;
	
	
	public String getPassing_Grade() {
		return Passing_Grade;
	}
	public void setPassing_Grade(String passing_Grade) {
		Passing_Grade = passing_Grade;
	}
	public int getEvent_ID() {
		return Event_ID;
	}
	public void setEvent_ID(int event_ID) {
		Event_ID = event_ID;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public Double getCost() {
		return Cost;
	}
	public void setCost(Double cost) {
		Cost = cost;
	}
	public String getGrading_Format() {
		return Grading_Format;
	}
	public void setGrading_Format(String grading_Format) {
		Grading_Format = grading_Format;
	}
	public String getEvent_Type() {
		return Event_Type;
	}
	public void setEvent_Type(String event_Type) {
		Event_Type = event_Type;
	}
	public String getJustification() {
		return Justification;
	}
	public void setJustification(String justification) {
		Justification = justification;
	}
	
	
}
