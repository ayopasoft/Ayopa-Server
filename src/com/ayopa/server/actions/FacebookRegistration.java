package com.ayopa.server.actions;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.ayopa.server.model.Buyer;
import com.ayopa.server.model.persistence.AuctionPersistence;
import com.ayopa.server.model.persistence.BuyerPersistence;
import com.ayopa.server.utils.FBUtils;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;



@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS,  location="FacebookRegistration.jsp"),
})
public class FacebookRegistration extends ActionSupport implements ServletRequestAware{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private String isUser;
	private static Log log = LogFactory.getLog(FacebookRegistration.class);
	
	
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}		

	public HttpServletRequest getRequest() {
		return request;
	}
	
	public String getIsUser() {
		return isUser;
	}

	public void setIsUser(String isUser) {
		this.isUser = isUser;
	}


	@Override
	public String execute() throws Exception {
		String fbCookie = null;
		Map<String,String> map = new HashMap<String,String>();
		Buyer buyer = new Buyer();
		BuyerPersistence bp = new BuyerPersistence();
		
		isUser = "false";
		
		
		try {
			for(Cookie c : request.getCookies()) {
			    if (c.getName().equals("fbs_"+ FBUtils.FACEBOOK_API_KEY))
			      fbCookie=c.getValue();
			  }
			
			if (fbCookie != null) {
				
				map = FBUtils.parseFBCookie(fbCookie);
				
				if (map.containsKey("uid")){
					buyer = bp.getBuyer(map.get("uid"));
				}
				
				if (buyer.getBuyer_id() != null)
					isUser = "true";
				
			}
		} catch (Exception e) {
			
			log.error("Problem reading FB cookie " + e);
		}
		 return Action.SUCCESS;
		 
	}     
	
}
