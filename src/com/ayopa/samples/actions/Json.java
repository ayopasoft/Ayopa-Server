package com.ayopa.samples.actions;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage (value="application")
@Results({
	@Result( name=Action.INPUT,   location="Home.jsp" ),
	@Result( name=Action.SUCCESS, type="json",   params={ "root", "foobar" } ),

})
public class Json extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	private int inputvalue;
	
	private Map foobar;
	
	public Map getFoobar () {
		return foobar;
	}
	

	public int getInputvalue() {
		return inputvalue;
	}

	public void setInputvalue(int inputvalue) {
		this.inputvalue = inputvalue;
	}

	@Override
	public String execute() throws Exception {
		foobar = new HashMap ();
		foobar.put("input", inputvalue);
		return Action.SUCCESS;
	}

	@Override
	public void validate() {
		if (inputvalue < 0)
			addFieldError("inputvalue", "Field must be positive");
	}
	
}
