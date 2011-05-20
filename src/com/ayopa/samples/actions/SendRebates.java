package com.ayopa.samples.actions;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;


import com.ayopa.server.model.Auction;
import com.ayopa.server.model.Invoice;
import com.ayopa.server.model.Rebate;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS, type="string", params={ "contentType", "text/plain", "property", "jsonReturn" } ),
})
public class SendRebates extends ActionSupport {
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
		
		List<Invoice> invoices = new ArrayList<Invoice>();
		List<Rebate> rebates = new ArrayList<Rebate>();
		List<Auction> auctions = new ArrayList<Auction>();
		
		auctions = Auction.getAuctionsforRebate();
		
		//invoices = Invoice.getInvoicesForMerchants(auctions);
		
		rebates = Rebate.getRebatesforConsumers(auctions);
		String response = Rebate.sendRebates();
		
		
		
		JSONArray jsonObject = (JSONArray) JSONSerializer.toJSON(rebates);
		
		String jsonString = response + jsonObject.toString();
		
	
		
		
		if ( jsoncallback != null  ) jsonReturn = jsoncallback + "(" + jsonString + ");";
		else jsonReturn = jsonString;
		
		return Action.SUCCESS;
	}
	
	
	
}
