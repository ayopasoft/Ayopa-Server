package com.ayopa.server.actions;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.CookiesAware;

import com.ayopa.server.utils.FBUtils;
import com.ayopa.server.utils.HTTPRequestPoster;
import com.ayopa.server.utils.JsonUtils;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;

import freemarker.template.utility.StringUtil;



@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS, type="string", params={ "contentType", "text/plain", "property", "jsonReturn" } ),
	
})
public class GetUserByFbId extends ActionSupport implements CookiesAware{
	private static final long serialVersionUID = 1L;
	private Map<String,String> cookiesMap;
	
	private String code;
	private String fbID;
	private String jsoncallback;
	private String jsonReturn;
	private String jsonString;
	private HttpServletResponse sr1;
	
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

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
		
		
		//String accessToken = FBUtils.getAccessTokenFromCookieValue(cookiesMap.get("fbs_186996844658023"), "access_token");
		
        
        String loginURL = "http://localhost:8080/AyopaServer/get-facebook-id";
        //loginURL = "https://www.facebook.com/login.php?api_key=186996844658023&skip_api_login=1&display=page";
        //loginURL = "https://graph.facebook.com/oauth/access_token?client_id=186996844658023&redirect_uri=http://localhost:8080/AyopaServer/home&client_secret=4db64bc60336d88d8547dcfb059cd7b6&type=client_cred&code=";
        
        
        URL logURL = new URL(loginURL);
        
        URLConnection uconn = logURL.openConnection( );
        HttpURLConnection conn = (HttpURLConnection)uconn;
        conn.setInstanceFollowRedirects( true );
        conn.setConnectTimeout( 10000 );
        conn.setReadTimeout( 10000 );


        conn.connect();
        System.out.println(conn.getResponseMessage());
        
       
        InputStreamReader in = new InputStreamReader((InputStream) conn.getContent());
        BufferedReader buff = new BufferedReader(in);
        
        String line;
        do {
          line = buff.readLine();
            System.out.println(line);
        } while (line != null);
        
        //System.out.println("Next URL 3:" + nextList2.get(0));
        
        //System.out.println("Next URL 3: " + nextList2.get(0));
        
        //jsonReturn = nextList2.toString();
        
		
		
		//System.out.println(session.get("fbs_"+FBUtils.FACEBOOK_API_KEY));
        //jsonReturn = (String) session.get("fbs_"+FBUtils.FACEBOOK_API_KEY);
        
        /*String accessToken = "";
		
		 accessToken = HTTPRequestPoster.sendGetRequest("https://graph.facebook.com/oauth/access_token"
				     , "client_id=" + FBUtils.FACEBOOK_API_KEY + "&redirect_uri=" + FBUtils.FACEBOOK_AUTH_REDIRECT_URL + "&client_secret=" + FBUtils.FACEBOOK_APPLICATION_SECRET + "&code=" + code);
		 accessToken = accessToken.split("&")[0].replaceFirst("access_token=", "");
		 FacebookClient facebookClient = new DefaultFacebookClient(accessToken);
		 User user = facebookClient.fetchObject("me", User.class);
		 jsonString = "{\"User\": \"" + user.getId() + "\"}";
		 if ( jsoncallback != null ) jsonReturn = jsoncallback + "(" + jsonString + ");";
			else jsonReturn = jsonString;*/
		 
		 return Action.SUCCESS;
		
		}
		
			
		
		//"client_id=" + appID + "&client_secret=" + appSecret + "&grant_type=client_credentials"
		//System.out.println("Access Token: " + accessToken);
		
		//https://graph.facebook.com/oauth/authorize?client_id=120882414650116&redirect_uri=http://ayopa1dev.happyjacksoftware.com/AyopaServer/home
		//https://graph.facebook.com/oauth/access_token?client_id=120882414650116&redirect_uri=http://ayopa1dev.happyjacksoftware.com/consumer-rebate&client_secret=17ce975710ce3ac5670fa17d5e70fef3&code=1fd26b70b0a229b1bdf00c00-556659695|GscfJAysSw7TFkFJO55Z05lxKpQ
		
		
		
		//accessToken = accessToken.split("&")[0].replaceFirst("access_token=", "");
		
		//String accessToken = appID + "|" + appSecret;
		//FacebookClient facebookClient = new DefaultFacebookClient(accessToken);
		
		
		//User user = facebookClient.fetchObject("me", User.class);
		
		//System.out.println("AccessToken:"+ accessToken);
		
		//jsonString = "{\"User\": \"" + user.getId() + "\"}";
		//if ( jsoncallback != null ) jsonReturn = jsoncallback + "(" + jsonString + ");";
		//else jsonReturn = jsonString;
		

		
	//}


	


	
	     
	
}
