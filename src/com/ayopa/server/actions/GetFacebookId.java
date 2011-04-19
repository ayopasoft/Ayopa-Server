package com.ayopa.server.actions;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.ayopa.server.utils.FBUtils;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;



@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS, type="redirect", location= "${location}" ),
})
public class GetFacebookId extends ActionSupport {
	private static final long serialVersionUID = 1L;
	// These must match what's on the Facebook App and what's used in the 
	// call to https://graph.facebook.com/oauth/authorize
	
	private String location;
	private String jsoncallback;
	
	
	
	public String getJsoncallback() {
		return jsoncallback;
	}



	public void setJsoncallback(String jsoncallback) {
		this.jsoncallback = jsoncallback;
	}



	public String getLocation() {
		return location;
	}



	public void setLocation(String location) {
		this.location = location;
	}



	@Override
	public String execute() throws Exception {
		if (jsoncallback != null)
			location = FBUtils.getLoginURL(jsoncallback);
		else
			location = FBUtils.getLoginURL();
		
		System.out.println("Invoking: " + location);
		System.out.println("In Action: GetFacebookId jsoncallback = " + jsoncallback);
		 return Action.SUCCESS;
		
		}
		
}
