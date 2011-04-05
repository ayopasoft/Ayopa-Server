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
		
		String cancel_url = "http://ayopa1dev.happyjacksoftware.com:8080/AyopaServer/get-permissions/";
		String next_url = "http://ayopa1dev.happyjacksoftware.com:8080/AyopaServer/get-registration/";
		
		 url = "https://www.facebook.com/login.php?api_key=" + FBUtils.FACEBOOK_API_KEY + "&display=page&return_session=1&session_version=3&v=1.0&req_perms=publish_stream,email,offline_access,manage_pages&canvas=0&fbconnect=1&cancel_url=" + cancel_url + "&next_url=" + next_url;
		 return Action.SUCCESS;
		 
		 /*$cancel_url = 'http://www.ayopadev.com/ayopa/process_register.php';
		 $next_url = 'http://www.ayopadev.com/ayopa/register.php';

		 $url = $facebook->getLoginUrl(array('req_perms' =>'publish_stream,email,offline_access','canvas'=> 0,'fbconnect'=> 1,'cancel_url'=>$cancel_url,'next'=>$next_url));
		
		 
		 
		 'api_key'         => $this->getAppId(),
	        'cancel_url'      => $currentUrl,
	        'display'         => 'page',
	        'fbconnect'       => 1,
	        'next'            => $currentUrl,
	        'return_session'  => 1,
	        'session_version' => 3,
	        'v'               => '1.0',*/
	}


	


	
	     
	
}