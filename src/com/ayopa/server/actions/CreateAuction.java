package com.ayopa.server.actions;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.ayopa.server.model.Auction;
import com.ayopa.server.utils.FBUtils;
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

	
	public void setJsoncallback(String jsoncallback) {
		//this.jsoncallback = JsonUtils.sanitizeJsonpParam(jsoncallback);
		this.jsoncallback = jsoncallback;
	}
	
	public void setAuctionDef(String auctionDef) {
		this.auctionDef = auctionDef;
	}
		
	public String getJsonReturn() {
		return jsonReturn;
	}

	@Override
	public String execute() throws Exception {
		
		Auction auction = new Auction();
		
		String jsonString = auction.putAuction(auctionDef);
		
		if ( jsoncallback != null || jsoncallback != "" ) jsonReturn = jsoncallback + "(" + jsonString + ");";
		else jsonReturn = jsonString;
		
		return Action.SUCCESS;
	}
	
	
	
}
