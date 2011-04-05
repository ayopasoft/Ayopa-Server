package com.ayopa.server.actions;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS, location="Index.jsp" )	
})
public class Index extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	public String getGreeting() {
		return "Hello, world!";
	}

	@Override
	public String execute() throws Exception {
		return Action.SUCCESS;
	}

}
