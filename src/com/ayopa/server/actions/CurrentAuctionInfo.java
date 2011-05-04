package com.ayopa.server.actions;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.ayopa.server.model.Auction;
import com.ayopa.server.model.CurrentAuction;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS, type="string", params={ "contentType", "text/plain", "property", "jsonReturn" } ),
})
public class CurrentAuctionInfo extends ActionSupport {
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
		
		Auction auction = new Auction();
		CurrentAuction currAuction = new CurrentAuction();
		Map<String,Object> map = new HashMap<String,Object>();
		
		//String validates = auction.validateAuction(auctionDef);
		
		auction = auction.getAuction(auctionID);
		map = currAuction.getCurrentQuantity(auctionID);
		int quantity = (Integer) map.get("quantity");
		currAuction = CurrentAuction.getCurrentAuctionInfo(auction, quantity);
		map = CurrentAuction.getCurrentAuctionRebate(auction.getMerchant_id(), currAuction.getCurrent_price(), (Double) map.get("total"), quantity);
		currAuction.setRebate_total((Double) map.get("rebate_total"));
		currAuction.setAuction_total((Double) map.get("auction_total"));
		currAuction.setCommission_total((Double) map.get("commission_total"));
		
		
		JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(currAuction);
		
		String jsonString = jsonObject.toString();
		
		if ( jsoncallback != null  ) jsonReturn = jsoncallback + "(" + jsonString + ");";
		else jsonReturn = jsonString;
		
		return Action.SUCCESS;
	}
	
	
	
}
