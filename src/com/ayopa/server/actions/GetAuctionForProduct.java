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
		
		Auction myAuction = new Auction();
		merchantID = "1";
		productID = "4";
		
		jsonString = myAuction.auctionToJson(myAuction.getAuctionForProduct(merchantID, productID));
		
		return Action.SUCCESS;
	}
	
}
