package com.ayopa.samples.actions;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;



@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS, type="redirect", location="https://graph.facebook.com/oauth/authorize?client_id=" + GetFaceBookId.APPID + "&redirect_uri=" + GetFaceBookId.APPURI ),
})
public class GetFaceBookId extends ActionSupport {
	private static final long serialVersionUID = 1L;
	// These must match what's on the Facebook App and what's used in the 
	// call to https://graph.facebook.com/oauth/authorize
	public static final String APPID  = "194905877218093";
	public static final String APPURI = "http://aws.gamboas.org:8080/AyopaServer/get-face-book-id-response.action";
	public static final String APPSECRET = "83ad86400bc4ed3550f35b997a9afeac";
	
	@Override
	public String execute() throws Exception {
		 return Action.SUCCESS;
		
		}
		
}
