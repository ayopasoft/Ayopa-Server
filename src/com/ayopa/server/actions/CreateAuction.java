package com.ayopa.server.actions;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.ayopa.server.utils.JsonUtils;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS, type="string", params={ "contentType", "text/plain", "property", "jsonReturn" } ),
})
public class CreateAuction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private String auctionDef;
	private String jsoncallback;
	private String jsonReturn;

	private Map<String, Object> auction;
	
	public void setJsoncallback(String jsoncallback) {
		this.jsoncallback = JsonUtils.sanitizeJsonpParam(jsoncallback);
	}
	
	public void setAuctionDef(String auctionDef) {
		this.auctionDef = auctionDef;
	}
		
	public String getJsonReturn() {
		return jsonReturn;
	}

	@Override
	public String execute() throws Exception {
		auction = new HashMap<String, Object> ();
		auction.put("auction_id", 123456);
		
		JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON( auction ); 
		
		if ( jsoncallback != null ) jsonReturn = jsoncallback + "(" + jsonObject.toString() + ");";
		else jsonReturn = jsonObject.toString();
		
		return Action.SUCCESS;
	}
	
	
	
}
