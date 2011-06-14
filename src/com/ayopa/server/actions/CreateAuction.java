package com.ayopa.server.actions;

import org.apache.log4j.Logger;
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
public class CreateAuction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	private static org.apache.log4j.Logger logger = Logger.getLogger(CreateAuction.class);
	
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
		String jsonString = "";
		
		try {
			boolean validates = auction.validateAuction(auctionDef);
			
			if (validates)
				jsonString = auction.putAuction(auctionDef);
		
		} catch (Exception e) {
			
			logger.error("Problem validating create auction: " + e.getMessage() + "\n" + auctionDef);
			//e.printStackTrace();
			jsonString = "{error:\"" + e.getMessage() + "\"}";
		}
		
		if ( jsoncallback != null && !jsoncallback.equals("") ) jsonReturn = jsoncallback + "(" + jsonString + ");";
		else jsonReturn = jsonString;
		
		return Action.SUCCESS;
	}
	
	
	
}
