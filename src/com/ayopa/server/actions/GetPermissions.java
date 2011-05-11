package com.ayopa.server.actions;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.ayopa.server.utils.FBUtils;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;



@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS, type="redirect", location="${url}"),
})
public class GetPermissions extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String url;

	public String getUrl()
	{
	 return url;
	}
	
	@Override
	public String execute() throws Exception {
		
		
		 url = "https://www.facebook.com/login.php?api_key=" + FBUtils.FACEBOOK_API_KEY + "&display=page&return_session=1&session_version=3&v=1.0&req_perms=publish_stream,email,offline_access&canvas=0&fbconnect=1&cancel_url=" + FBUtils.FACEBOOK_CANCEL_URL + "&next=" + FBUtils.FACEBOOK_NEXT_URL;
		 return Action.SUCCESS;
		
	}


	


	
	     
	
}
