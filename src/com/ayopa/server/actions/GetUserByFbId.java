package com.ayopa.server.actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.ayopa.server.utils.HTTPRequestPoster;
import com.ayopa.server.utils.JsonUtils;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;


@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS, type="string", params={ "contentType", "text/plain", "property", "jsonReturn" } ),
})
public class GetUserByFbId extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private String fbID;
	private String jsoncallback;
	private String jsonReturn;
	private String jsonString;
	

	public void setFbID(String fbID) {
		this.fbID = fbID;
	}
	

	public void setJsoncallback(String jsoncallback) {
		this.jsoncallback = JsonUtils.sanitizeJsonpParam(jsoncallback);
	}


	public String getJsoncallback() {
		return jsoncallback;
	}


	public String getJsonReturn() {
		return jsonReturn;
	}

	
	@Override
	public String execute() throws Exception {
		String appID = "120882414650116";
		String appSecret = "17ce975710ce3ac5670fa17d5e70fef3";
		
		String accessToken = "";
		
		accessToken = HTTPRequestPoster.sendGetRequest("https://graph.facebook.com/oauth/access_token"
		     , "client_id=" + appID + "&client_secret=" + appSecret + "&grant_type=client_credentials");
		
		accessToken = accessToken.split("&")[0].replaceFirst("access_token=", "");
		
		FacebookClient facebookClient = new DefaultFacebookClient(accessToken);
		
		@SuppressWarnings("rawtypes")
		User user = facebookClient.fetchObject("me", User.class);
		
		System.out.println("AccessToken:"+ accessToken);
		
		jsonString = "{\"User\": \"" + user.getId() + "\"}";
		if ( jsoncallback != null ) jsonReturn = jsoncallback + "(" + jsonString + ");";
		else jsonReturn = jsonString;

		return Action.SUCCESS;
	}
	     
	
}
