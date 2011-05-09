package com.ayopa.server.actions;

import java.net.URLDecoder;

import javax.servlet.http.Cookie;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

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
	@Result( name=Action.SUCCESS, location="ProcessPermissions.jsp" ),

})
public class ProcessPermissions extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private String session;
	private String access_token;
	private String base_domain;
	//define getters for data elements
	
	
	
	public String getSession() {
		return session;
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
			Cookie cookie = new Cookie("fbs_"+FBUtils.FACEBOOK_API_KEY,session);
			cookie.setDomain(sess.getString("base_domain"));
			base_domain = sess.getString("base_domain");
			User user = facebookClient.fetchObject("me", User.class);
			 
			 buyer.setBuyer_id(user.getId());
			 buyer.setBuyer_name(user.getName());
			 buyer.setBuyer_email(user.getEmail());
			 buyer.setBuyer_access_token(access_token);
			 
			 bp.putBuyer(buyer);
		}
		
		
		
		return Action.SUCCESS;
	}



	
	


	
}
