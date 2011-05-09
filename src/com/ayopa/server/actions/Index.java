package com.ayopa.server.actions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.SessionAware;

import com.ayopa.server.model.Buyer;
import com.ayopa.server.model.persistence.BuyerPersistence;
import com.ayopa.server.utils.FBUtils;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;

@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS, location="Index.jsp" ),
	@Result( name="consumerRedirect", type="redirectAction", params={ "actionName", "consumer-canvas" }),
	@Result( name="permsRedirect", location="PermissionsRedirect.jsp"),
	@Result( name="permsCallback", location="PermissionsCallback.jsp")
	
})
public class Index extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 1L;

	private String signed_request;
	private String buyer_id;
	private String perms_url;
	private String code;
	
	
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	private Map<String, Object> session;

	public void setSession(Map<String,Object> map) {
		this.session = map;
	}
	
	public String getPerms_url() {
		return perms_url;
	}


	public String getBuyer_id() {
		return buyer_id;
	}


	public void setSigned_request(String signed_request) {
		this.signed_request = signed_request;
	}


	@Override
	public String execute() throws Exception {
		//detect user type
		//redirect to consumer if consumer
		//redirect to merchant if merchant
		//otherwise display canvas page
	
		JSONObject jsonObject = new JSONObject();
		BuyerPersistence bp = new BuyerPersistence();
		Buyer buyer = new Buyer();
		
		System.out.println(signed_request);
		
		try {
			jsonObject = FBUtils.parseSignedRequest(signed_request);
			buyer = bp.getBuyer(jsonObject.getString("user_id"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOG.error("Problem with signed request", e);
		}
		
		if (code != null)
		{
			String next_url = "http://ayopa1dev.happyjacksoftware.com:8080/AyopaServer/";
			String access_token = "";
			String token_url = "https://graph.facebook.com/oauth/access_token?client_id="
		        + FBUtils.FACEBOOK_API_KEY + "&redirect_uri=" + URLEncoder.encode(next_url,"UTF-8") + "&client_secret="
		        + FBUtils.FACEBOOK_APPLICATION_SECRET + "&code=" + code;
			
			URL token = new URL(token_url);
			BufferedReader in = new BufferedReader(
						new InputStreamReader(
						token.openStream()));

			String inputLine;

			while ((inputLine = in.readLine()) != null)
			   access_token += inputLine; 

			in.close();
			
			for (String s : access_token.split("&") ) {
	        	if (s.startsWith("access_token="))
	        		access_token = s.replaceFirst("access_token=", "");
	        }
			
			FacebookClient facebookClient = new DefaultFacebookClient(access_token);
			 User user = facebookClient.fetchObject("me", User.class);
			 
			 buyer.setBuyer_id(user.getId());
			 buyer.setBuyer_name(user.getName());
			 buyer.setBuyer_email(user.getEmail());
			 buyer.setBuyer_access_token(access_token);
			 
			 bp.putBuyer(buyer);
			 
			 session.put("buyer_id", user.getId());
			 
			 return "permsCallback";

		}
		
		if (buyer.getBuyer_id() != null)
		{
			buyer_id = buyer.getBuyer_id();
			 
		    session.put("buyer_id", buyer_id);
			return "consumerRedirect";
		}
		else if (jsonObject.containsKey("user_id"))
		{
			return Action.SUCCESS;
		}
		else
		{
			String cancel_url = "http://ayopa1dev.happyjacksoftware.com:8080/AyopaServer/";
			String next_url = "http://ayopa1dev.happyjacksoftware.com:8080/AyopaServer/";
			
			//perms_url = "https://www.facebook.com/login.php?api_key=" + FBUtils.DEV_APPID + "&req_perms=publish_stream,email,offline_access,manage_pages&canvas=1&fbconnect=0&return_session=0&cancel_url=" + cancel_url + "&next=" + next_url;
			perms_url = "https://www.facebook.com/dialog/oauth?client_id=" + FBUtils.FACEBOOK_API_KEY + "&redirect_uri=" + next_url + "&scope=publish_stream,email,offline_access,manage_pages&canvas=1&fbconnect=0";
			return "permsRedirect";
		}
		
	}
}

