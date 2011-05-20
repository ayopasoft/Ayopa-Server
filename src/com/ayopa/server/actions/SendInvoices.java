package com.ayopa.server.actions;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.ayopa.server.model.Invoice;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS, type="string", params={ "contentType", "text/plain", "property", "jsonReturn" } ),
})
public class SendInvoices extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private String auctionID;
	private String jsoncallback;
	private String jsonReturn;

	
	
	
	public String getAuctionID() {
		return auctionID;
	}



	public void setAuctionID(String auctionID) {
		this.auctionID = auctionID;
	}



	public void setJsoncallback(String jsoncallback) {
		//this.jsoncallback = JsonUtils.sanitizeJsonpParam(jsoncallback);
		this.jsoncallback = jsoncallback;
	}
	
	
		
	public String getJsonReturn() {
		return jsonReturn;
	}

	@Override
	public String execute() throws Exception {
		
		Invoice.sendInvoices();
		
		String jsonString = "SUCCESS!";
		
		if ( jsoncallback != null  ) jsonReturn = jsoncallback + "(" + jsonString + ");";
		else jsonReturn = jsonString;
		
		return Action.SUCCESS;
	}
	
	
	
}
