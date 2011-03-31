package com.ayopa.server.actions;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.exception.FacebookException;

@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS, location="Canvas.jsp" ),

})
public class FbTest extends ActionSupport {
	private static final long serialVersionUID = 1L;

	
	private static final String FACEBOOK_API_KEY = "120882414650116";
	private static final String FACEBOOK_APPLICATION_SECRET = "17ce975710ce3ac5670fa17d5e70fef3";
	private FacebookClient facebookClient;
	private String facebookAuthToken = null;
	private String facebookSessionKey = null;
	
	@SuppressWarnings("unused")
	private String getUserTypeById() {
		String user_type = "";
	
		return user_type;
	}

	@Override
	public String execute() throws Exception {
		//detect user type
		//redirect to consumer if consumer
		//redirect to merchant if merchant
		//otherwise display canvas page
		
		facebookClient = new DefaultFacebookClient(accessToken);

		// I always call facebookAuthorise
		// First time users would click a button that calls facebookConnect() first to grab the session key.
		facebookAuthorise();
		
		return Action.SUCCESS;
	}
	

	private void facebookConnect() throws FacebookException {
		// auth.createToken returns a string
		// http://wiki.developers.facebook.com/index.php/Auth.createToken
		facebookAuthToken = facebookClient.executeQuery("auth.createToken", String.class);
		String url = "http://www.facebook.com/login.php"
			+ "?api_key=" + FACEBOOK_API_KEY
			+ "&fbconnect=true" + "&v=1.0"
			+ "&connect_display=page" + "&session_key_only=true"
			+ "&req_perms=read_stream,publish_stream,offline_access"
			+ "&auth_token=" + facebookAuthToken;

		// Here we launch a browser with the above URL so the user can login to Facebook, grant our requested permissions and send our token for pickup later
		if (Desktop.isDesktopSupported()) {
			Desktop desktop = Desktop.getDesktop();

			if (desktop.isSupported(Desktop.Action.BROWSE)) {
				try {
					desktop.browse(new URI(url));
				}
				catch(IOException ioe) {
					ioe.printStackTrace();
				}
				catch(URISyntaxException use) {
					use.printStackTrace();
				}
			}
		}
	}

	private void facebookAuthorise() throws FacebookException {
		// I saved my session key to a .properties file for future use so the user doesn't keep have to authorising my app!
		// auth.getSession returns JSON so we need to decode it just to grab the session key
		// http://wiki.developers.facebook.com/index.php/Auth.getSession
		// Here we pickup the session key and define the authToken we used above in facebookConnect()
		JSONObject json = new JSONObject(facebookClient.executeQuery("auth.getSession", String.class, Parameter.with("auth_token", facebookAuthToken)));
		facebookSessionKey = json.getString("session_key");

		// An example call, you can literally use anything from the REST API
		Long uid = facebookClient.executeQuery("users.getLoggedInUser", facebookSessionKey);
		System.out.println("FB User ID: " + uid);
	}

}
