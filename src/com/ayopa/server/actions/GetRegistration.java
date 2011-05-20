package com.ayopa.server.actions;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;



@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS,  location="GetRegistration.jsp"),
})
public class GetRegistration extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	
	@Override
	public String execute() throws Exception {
		
		 return Action.SUCCESS;
		 
	}     
	
}
