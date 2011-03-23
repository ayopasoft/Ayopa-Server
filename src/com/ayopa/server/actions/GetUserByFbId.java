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

import com.ayopa.server.utils.JsonUtils;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;


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
		FacebookClient facebookClient = new DefaultFacebookClient();
		
		@SuppressWarnings("rawtypes")
		List user = facebookClient.executeQuery("SELECT id FROM profile where id = me()", List.class);
		System.out.println("UserID:" + user.size());
		
		jsonString = "{\"User\": \"" + fbID + "\"}";
		if ( jsoncallback != null ) jsonReturn = jsoncallback + "(" + jsonString + ");";
		else jsonReturn = jsonString;

		return Action.SUCCESS;
	}
	

	
	private String statusMsg;
	String appid = "120882414650116";
	String appsecret = "17ce975710ce3ac5670fa17d5e70fef3";
	String redirectURL = "http://localhost:8080/project_fb/facebookjsp.jsp";
	String authorizeURLStr = "https://graph.facebook.com/oauth/authorize?client_id="
	+ appid + "&redirect_uri=" + redirectURL;
	String accessTokenURLStr;
	String accessToken;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		if(request.getParameter("accessToken")!=null){
			
			return;
		}
		System.out.println("in request");
		response.sendRedirect(authorizeURLStr);
		statusMsg=request.getParameter("fbstatus");
		System.out.println("after sending request");
		
		accessTokenURLStr = "https://graph.facebook.com/oauth/access_token?client_id="
		      + appid + "&redirect_uri=" + redirectURL + "&client_secret=" + appsecret
		      + "&code=" + statusMsg +"&type=web_server";
		
		accessToken=readUrl(accessTokenURLStr).split("&")[0].replaceFirst("access_token=", "");
		
		
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	  
	private String readUrl(String urlString) throws IOException {
	    URL url = new URL(urlString);	    
	    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
	 
	    String response = "";
	    String inputLine;
	 
	    while ((inputLine = in.readLine()) != null)
	        response += inputLine;
	 
	    in.close();
	    return response;
	}

	 
	     
	
}
