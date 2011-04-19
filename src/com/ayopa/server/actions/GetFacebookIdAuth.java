package com.ayopa.server.actions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.ayopa.server.utils.FBUtils;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;



@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS, type="string", params={ "contentType", "text/plain", "property", "jsonReturn" } ),
})
public class GetFacebookIdAuth extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	private String error;
	private String errorReason;
	private String errorDescription;
	private String code;
	private String accessToken;
	private String jsoncallback;
	private String jsonString;
	private String jsonReturn;
	
	
	
	public String getJsonReturn() {
		return jsonReturn;
	}

	public void setJsonReturn(String jsonReturn) {
		this.jsonReturn = jsonReturn;
	}

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

	public String getJsoncallback() {
		return jsoncallback;
	}

	public void setJsoncallback(String jsoncallback) {
		this.jsoncallback = jsoncallback;
	}

	public void setError(String error) {
		this.error = error;
	}

	public void setErrorReason(String errorReason) {
		this.errorReason = errorReason;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getError() {
		return error;
	}

	public String getErrorReason() {
		return errorReason;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public String getCode() {
		return code;
	}

	public String getAccessToken() {
		return accessToken;
	}

	@Override
	public String execute() throws Exception {
		System.out.println ("In Action: GetFaceBookIdResponse");
		
		if (code == null || "".equals(code))
			return Action.ERROR;
		
		System.out.println ("In Action: GetFaceBookIdResponse.  Code = " + code);

		/*String url = "https://graph.facebook.com/oauth/access_token?" +
        						"client_id=" + FBUtils.APPID + "&" +
        						"redirect_uri=" + GetFaceBookId.APPURI + "&" +
        						"client_secret=" + GetFaceBookId.APPSECRET + "&" +
        						"code=" + code;*/
		
		String url = "";
		
		if (jsoncallback != null){
			
			url = FBUtils.getAuthURL(jsoncallback, code);
		}
		else
		{
			url = FBUtils.getAuthURL(code);
		}
		
		
		System.out.println ("Invoking: " + url);
		
        URLConnection conn = new URL(url).openConnection();
        conn.setConnectTimeout( 10000 );
        conn.setReadTimeout( 10000 );
        
        conn.connect();

        accessToken = "";
        
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;

        while ((line = in.readLine()) != null) 
        	accessToken += line;
        in.close();
        
        for (String s : accessToken.split("&") ) {
        	if (s.startsWith("access_token="))
        		accessToken = s.replaceFirst("access_token=", "");
        }
        
		System.out.println ("In Action: GetFaceBookIdResponse.  AccessToken = " + accessToken);
		System.out.println("In Action: GetFaceBookIdResponse. jsoncallback = " + jsoncallback);
		
		FacebookClient facebookClient = new DefaultFacebookClient(accessToken);
		 User user = facebookClient.fetchObject("me", User.class);
		 
		 jsonString = "{\"User\": \"" + user.getId() + "\"}";
		
		 if ( jsoncallback != null) jsonReturn = jsoncallback + "(" + jsonString + ");";
			else jsonReturn = jsonString;
		
		return Action.SUCCESS;
	}
		
}
