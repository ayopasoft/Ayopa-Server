package com.ayopa.server.actions;

import java.util.Map;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.CookiesAware;

import com.ayopa.server.utils.FBUtils;
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
public class GetUserByFbId extends ActionSupport implements CookiesAware{
	private static final long serialVersionUID = 1L;
	private Map<String,String> cookiesMap;
	
	private String fbID;
	private String jsoncallback;
	private String jsonReturn;
	private String jsonString;
	
	public Map<String,String> getCookiesMap() {
		    return cookiesMap;
		  }
	
	 @Override
		public void setCookiesMap(Map<String, String> cookiesMap) {
			this.cookiesMap = cookiesMap;
			
		}

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
		String appID = "186996844658023";
		String appSecret = "17ce975710ce3ac5670fa17d5e70fef3";
		
		System.out.println(cookiesMap);
		
		//String accessToken = FBUtils.getAccessTokenFromCookieValue(cookiesMap.get("fbs_186996844658023"), "access_token");
		
		
		
		//accessToken = HTTPRequestPoster.sendGetRequest("https://graph.facebook.com/oauth/access_token"
		 //    , "client_id=" + appID + "&client_secret=" + appSecret + "&grant_type=client_credentials");
		
		//System.out.println(accessToken);
		
		
		
		//accessToken = accessToken.split("&")[0].replaceFirst("access_token=", "");
		
		//FacebookClient facebookClient = new DefaultFacebookClient(accessToken);
		
		
		//User user = facebookClient.fetchObject("me", User.class);
		
		//System.out.println("AccessToken:"+ accessToken);
		
		//jsonString = "{\"User\": \"" + user.getId() + "\"}";
		if ( jsoncallback != null ) jsonReturn = jsoncallback + "(" + jsonString + ");";
		else jsonReturn = jsonString;
		
		jsonReturn = cookiesMap.toString();

		return Action.SUCCESS;
	}


	


	
	     
	
}
