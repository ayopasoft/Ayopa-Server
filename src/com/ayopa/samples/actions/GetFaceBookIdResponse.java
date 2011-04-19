package com.ayopa.samples.actions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;



@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS, location="GetFaceBookIdResponse.jsp" ),
	@Result( name=Action.ERROR,   location="GetFaceBookIdResponse.jsp" ),
})
public class GetFaceBookIdResponse extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	private String error;
	private String errorReason;
	private String errorDescription;
	private String code;
	private String accessToken;
	
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

		String url = "https://graph.facebook.com/oauth/access_token?" +
        						"client_id=" + GetFaceBookId.APPID + "&" +
        						"redirect_uri=" + GetFaceBookId.APPURI + "&" +
        						"client_secret=" + GetFaceBookId.APPSECRET + "&" +
        						"code=" + code;
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

		return Action.SUCCESS;
	}
		
}
