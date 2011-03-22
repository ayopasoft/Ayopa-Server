package com.ayopa.samples.actions;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS, location="Home.jsp" ),	
	@Result( name=Action.INPUT,   location="Home.jsp" )
})
public class Home extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	private int inputvalue;
	

	public int getInputvalue() {
		return inputvalue;
	}

	public void setInputvalue(int inputvalue) {
		this.inputvalue = inputvalue;
	}

	@Override
	public String execute() throws Exception {
		return Action.SUCCESS;
	}

	@Override
	public void validate() {
		addActionError("Input Value: " + inputvalue);
	}
	
}
