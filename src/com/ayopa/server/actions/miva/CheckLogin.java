package com.ayopa.server.actions.miva;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS, type="string", params={ "contentType", "text/plain", "property", "merchantID" } ),

})
public class CheckLogin extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	
	private String merchantID;

	public String getMerchantID() {
		return merchantID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String execute() throws Exception {
		if (username == null || username.trim().length() == 0)
			merchantID = "0";
		else if (password == null || password.trim().length() == 0)
			merchantID = "0";
		else
			merchantID = "100";
		return Action.SUCCESS;
	}
}
