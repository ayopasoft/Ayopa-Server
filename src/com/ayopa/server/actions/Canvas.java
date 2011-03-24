package com.ayopa.server.actions;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS, location="Canvas.jsp" ),

})
public class Canvas extends ActionSupport {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private String getUserTypeById() {
		String user_type = "";
	
		return user_type;
	}

	@Override
	public String execute() throws Exception {
		//detect user type
		//redirect to consumer if consumer
		//redirect to merchant if merchant
		//otherwise display canvas page
	
		return Action.SUCCESS;
	}
}
