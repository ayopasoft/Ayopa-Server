package com.ayopa.server.actions;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.ayopa.server.model.Auction;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage (value="application")
@Results({
	//@Result( name=Action.SUCCESS, type="json",   params={ "root", "jsonString" } )
	@Result( name=Action.SUCCESS, type="string", params={ "contentType", "text/plain", "property", "jsonString" } ),
})
public class GetAuctionForProduct extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private String merchantID;
	private String productID;

	private String jsonString;
	
	public void setMerchantID(String merchantID) {
		this.merchantID = merchantID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}
	
	

	public String getJsonString() {
		return jsonString;
	}

	@Override
	public String execute() throws Exception {
		
		Auction auction = new Auction();
				
		auction = auction.getAuctionForProduct(merchantID, productID);
		
		if (auction.getAuction_id() == null)
			jsonString = "0";
		else
			jsonString = auction.auctionToJson(auction);
		
		return Action.SUCCESS;
	}
	
}
