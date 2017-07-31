package com.MSTS.TRMS_Project1;
import com.MSTS.TRMS_Project1.dao.*;
import com.MSTS.TRMS_Project1.servlet.*;

public class TestDriver {

	public static void main(String[] args) {
		Employee em = new Employee();
		em = em.login("MSEIF", "WAFFLES");
		//System.out.println(em);
		Application app = new Application();
		Event ev = new Event();
		Attachment att = new Attachment();
		
		
		
		
		//Test create event
		
		/*ev.setEvent_ID(1);
		ev.setDate("31.JUL.2017");
		ev.setLocation("Here");
		ev.setCost(2000d);
		ev.setGrading_Format("Letter grade");
		ev.setPassing_Grade("C");
		ev.setEvent_Type("certification");
		ev.setJustification("for SCIENCE");
		em.newEvent(ev);*/
		
		//Test create application
		
		app.setApp_ID(1);
		app.setE_ID(1);
		app.setDS_ID(2);
		app.setDS_approval(0);
		app.setDH_ID(2);
		app.setDH_approval(0);
		app.setBC_ID(10);
		app.setBenCo_approval(0);
		app.setEvent_ID(1);
		app.setStatus("pending");
		app.setAward(1000);
		
		em.apply(app);
		System.out.println("Application: ID=" + app.App_ID + " successfully saved!");
		
		int retint;
		retint = em.approve(1);
		if(retint>0)
		{
			System.out.println("Application was approved!");
		}
		else
		{
			System.out.println("Application was not approved!");
		}
		//Test Benco approve/reject function
		Employee benco = new BenCo();
		benco = benco.login("benny", "rage");
		benco.approve(1);
		benco.reject(1, "I'm on vacation");
		
		//Test DS/DH approve/reject functions
		Employee head = new Employee();
		head = head.login("yesman", "yes");
		head.approve(1);
		head.reject(1, "Not enough yes");
		
		//Test clarify function
		head.clarify(1, 1, "Send in your timesheet this week");
		
		//Test application cancel function
		em.cancel(1);
		
		//Test attach doc function
		att.setApp_ID(1);
		att.setDoc_Name("Timesheet.pdf");
		att.setDescription("Timesheets for week of event");
		em.attachDoc(att);
		
		//Test upload final function
		em.uploadFinal(app, "A");
		
		//Test view application status function
		em.viewStatus(1);
		
		//Test view ALL associated applications
		System.out.println(em.getAssociatedApplications(em));
		
		
	}

}
