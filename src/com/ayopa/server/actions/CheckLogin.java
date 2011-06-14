package com.ayopa.server.actions;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.ayopa.server.model.Merchant;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS, type="string", params={ "contentType", "text/plain", "property", "jsonReturn" } ),

})
public class CheckLogin extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private static org.apache.log4j.Logger logger = Logger.getLogger(CheckLogin.class);

	private String username;
	private String password;
	
	private String merchantID;
	private String jsoncallback;
	private String jsonReturn;

	
	
	
	
	public String getJsoncallback() {
		return jsoncallback;
	}

	public void setJsoncallback(String jsoncallback) {
		this.jsoncallback = jsoncallback;
	}

	public String getJsonReturn() {
		return jsonReturn;
	}

	public void setJsonReturn(String jsonReturn) {
		this.jsonReturn = jsonReturn;
	}

	public String getMerchantID() {
		return merchantID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String execute() throws Exception {
		
		String jsonString = "";
		
		try {
			
			if (username != null && password != null){
				Merchant merchant = new Merchant();
	            merchant = merchant.authenticate_login(username, password);
				
	            Map<String, String> map = new HashMap<String, String>();
				map.put("merchant_id", merchant.getMerchant_id());
				map.put("merchant_name", merchant.getMerchant_name());
				map.put("merchant_website", merchant.getMerchant_website());
				JSONObject jsonObj = (JSONObject) JSONSerializer.toJSON( map );
				jsonString = jsonObj.toString();
				
				if (merchant.getMerchant_id() == null)
					jsonString = "{error:\"Ayopa Authentication Error\"}";
				}
			else
			{
				jsonString = "{error:\"Ayopa Authentication Error\"}";
			}
			
				
		}
		catch (Exception e)
		{
			logger.error("Ayopa Authentication error: " + e);
			jsonString = "{error:\"" + e.getMessage() + "\"}";
		}
		
		
		if ( jsoncallback != null && !jsoncallback.equals("") ) jsonReturn = jsoncallback + "(" + jsonString  + ");";
		else jsonReturn = jsonString;
		
		return Action.SUCCESS;
	}
}
