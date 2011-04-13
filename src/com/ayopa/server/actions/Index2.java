package com.ayopa.server.actions;

import java.util.Map;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS, type="redirectAction", params={ "actionName", "index3" })
})
public class Index2 extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 1L;

	@Override
	public String execute() throws Exception {
		session.put("user_id", "42");
		return Action.SUCCESS;
	}
	
	private Map<String, Object> session;
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}

