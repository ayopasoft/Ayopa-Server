package com.ayopa.server.actions.miva;


import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.ayopa.server.model.Auction;
import com.ayopa.server.model.CurrentAuction;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS, type="string", params={ "contentType", "text/plain", "property", "auctionID" } ),

})
public class GetCurrentAuctionForProduct extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private String merchantID;
	private String productID;

	private String auctionID;
	
	public void setMerchantID(String merchantID) {
		this.merchantID = merchantID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}


	public String getAuctionID() {
		return auctionID;
	}

	@Override
	public String execute() throws Exception {
		
		//detects if auction expired, ended, etc before returning auction_id
		//Basically, don't return id if auction is not active
		
		Auction auction = new Auction();
		CurrentAuction currAuction = new CurrentAuction();
		
		if (merchantID == null || merchantID.trim().length() == 0)
			auctionID = "0";
		else if (productID == null || productID.trim().length() == 0)
			auctionID = "0";
		else
			{
			
				auction = auction.getAuctionForProduct(merchantID, productID);
				
				if (auctionID != null){
					if (auction.getAuction_maxunits() > currAuction.getCurrentQuantity(auction.getAuction_id())){
						auctionID = auction.getAuction_id();
					}
				}
				else{
					auctionID = "0";
				}
			}
		return Action.SUCCESS;
	}

	
}
