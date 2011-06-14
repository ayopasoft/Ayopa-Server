package com.ayopa.server.actions;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.ayopa.server.model.Buyer;
import com.ayopa.server.model.persistence.BuyerPersistence;
import com.ayopa.server.utils.FBUtils;
import com.ayopa.server.utils.JsonUtils;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;



@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS, type="string", params={ "contentType", "text/plain", "property", "jsonReturn" } ),
	
})
public class GetUserByFbId extends ActionSupport implements ServletRequestAware{
	private static final long serialVersionUID = 1L;
	private static org.apache.log4j.Logger logger = Logger.getLogger(GetUserByFbId.class);
	
	private String jsoncallback;
	private String jsonReturn;
	private String jsonString;
	private HttpServletRequest request;
	
	

	public String getJsonString() {
		return jsonString;
	}


	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}		

	public HttpServletRequest getRequest() {
		return request;
	}

   


	public void setJsonReturn(String jsonReturn) {
		this.jsonReturn = jsonReturn;
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
	
		String fbCookie = null;
		Map<String,String> map = new HashMap<String,String>();
		BuyerPersistence bp = new BuyerPersistence();
		Buyer buyer = new Buyer();
		
		jsonString = "{\"User\": \"" + "\"}";
		
		for(Cookie c : request.getCookies()) {
		    if (c.getName().equals("fbs_"+ FBUtils.FACEBOOK_API_KEY))
		      fbCookie=c.getValue();
		  }
		
		if (fbCookie != null) {
			
			map = FBUtils.parseFBCookie(fbCookie);
			
			if (map.containsKey("uid"))
			{
				buyer = bp.getBuyer(map.get("uid"));
				if (buyer.getBuyer_id() != null)
				{
					jsonString = "{\"User\": \"" + map.get("uid") + "\"}";
					logger.info("FB User ID: " + map.get("uid"));
				}
			}
			
		}	
			
			if ( jsoncallback != null) jsonReturn = jsoncallback + "(" + jsonString + ");";
				else jsonReturn = jsonString;
		
		
			return Action.SUCCESS;
	}
	
}
