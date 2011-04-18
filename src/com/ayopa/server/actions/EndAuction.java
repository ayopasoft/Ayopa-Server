package com.ayopa.server.actions;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.ayopa.server.model.Auction;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS, type="string", params={ "contentType", "text/plain", "property", "jsonReturn" } ),
	})
public class EndAuction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private String auctionID;
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


	public String getAuctionID() {
		return auctionID;
	}


	public void setAuctionID(String auctionID) {
		this.auctionID = auctionID;
	}


	@Override
	public String execute() throws Exception {
		
		Auction auction = new Auction();
				
		String jsonString = auction.endAuction(auctionID);
		
		if ( jsoncallback != null || jsoncallback != "" ) jsonReturn = jsoncallback + "(" + jsonString + ");";
		else jsonReturn = jsonString;
		
		return Action.SUCCESS;
	}
	
}
