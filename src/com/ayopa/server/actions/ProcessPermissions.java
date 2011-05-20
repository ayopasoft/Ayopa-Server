package com.ayopa.server.actions;

import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.ayopa.server.model.Buyer;
import com.ayopa.server.model.persistence.BuyerPersistence;
import com.ayopa.server.utils.FBUtils;
import com.ayopa.server.utils.Mail;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;

@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS, location="ProcessPermissions.jsp" ),

})
public class ProcessPermissions extends ActionSupport implements ServletResponseAware {
	private static final long serialVersionUID = 1L;

	private String session;
	private String access_token;
	private String base_domain;
	private String cookie_value;
	private HttpServletResponse response;
	//define getters for data elements
	

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		
	}
	
	public HttpServletResponse getServletResponse() {
		return response;
		
	}
	
	public String getSession() {
		return session;
	}



	public String getCookie_value() {
		return cookie_value;
	}



	public void setCookie_value(String cookie_value) {
		this.cookie_value = cookie_value;
	}



	public String getBase_domain() {
		return base_domain;
	}



	public void setBase_domain(String base_domain) {
		this.base_domain = base_domain;
	}



	public void setSession(String session) {
		this.session = session;
	}



	public String getAccess_token() {
		return access_token;
	}



	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}



	
	

	@Override
	public String execute() throws Exception {
		
		Buyer buyer = new Buyer();
		BuyerPersistence bp = new BuyerPersistence();
		
		//Map<String,String> map = new HashMap<String,String>();
		//map = FBUtils.ParseRegistration(signed_request);
		session = URLDecoder.decode(session,"UTF-8");
		JSONObject sess = (JSONObject) JSONSerializer.toJSON( session );
		
		if (sess.containsKey("access_token")){
			access_token = sess.getString("access_token");
			System.out.println(access_token);
			FacebookClient facebookClient = new DefaultFacebookClient(access_token);
			
			cookie_value = "access_token=" + sess.getString("access_token") + "&base_domain=" + sess.getString("base_domain")
			  + "&expires=0&secret=" + sess.getString("secret") + "&session_key=" + sess.getString("session_key")
			  + "&sig=" + sess.getString("sig") + "&uid=" + sess.getString("uid");
		
			Cookie cookie = new Cookie("fbs_" + FBUtils.FACEBOOK_API_KEY,cookie_value);
			cookie.setDomain(sess.getString("base_domain"));
			cookie.setPath("/");
			response.addCookie(cookie);
			
			base_domain = sess.getString("base_domain");
			User user = facebookClient.fetchObject("me", User.class);
			 
			buyer = bp.getBuyer(user.getId());
			
			if (buyer.getBuyer_id() == null)
			{
				//new user -- send email
				String[] recipient;
				recipient = new String[1];
				recipient[0] = user.getEmail();
			    String message = "Welcome to group savings with ayopa. " +
			    		"Track your savings and find more auctions at http://apps.facebook.com/ayopa_auctions. " +
			    		"By default, we will send your Paypal payments to your Facebook email address. " +
			    		"If you would like to change your Paypal email address, finish your account setup by creating " +
			    		"an account at www.ayopasoft.com.  Be sure to use your Facebook email address when you create your " +
			    		"ayopa account. Login and change your Paypal address in your account settings.";
				Mail.postMail(recipient, "Your ayopa account", message, "info@ayopasoft.com");
			}
			
			 buyer.setBuyer_id(user.getId());
			 buyer.setBuyer_name(user.getName());
			 buyer.setBuyer_email(user.getEmail());
			 buyer.setBuyer_access_token(access_token);
			 
			 bp.putBuyer(buyer);
		}
		
		
		
		return Action.SUCCESS;
	}






	
	


	
}
