package com.ayopa.server.actions.miva;


import org.apache.log4j.Logger;
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
	private static org.apache.log4j.Logger logger = Logger.getLogger(GetCurrentAuctionForProduct.class);
	
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
		
		logger.info("Get Current Auction For Product - Merchant ID:" + merchantID);
		logger.info("Get Current Auction For Product - Product ID:" + productID);
		
		
		Auction auction = new Auction();
		CurrentAuction currAuction = new CurrentAuction();
		
		if (merchantID == null || merchantID.trim().length() == 0)
			auctionID = "0";
		else if (productID == null || productID.trim().length() == 0)
			auctionID = "0";
		else
			{
				auctionID = "0";
				auction = auction.getCurrentAuctionForProduct(merchantID, productID);
			
				if (auction.getAuction_id() != null){
					if (auction.getAuction_maxunits() >= (Integer) currAuction.getCurrentQuantity(auction.getAuction_id()).get("quantity")){
						auctionID = auction.getAuction_id();
					}
				}
			}
		return Action.SUCCESS;
	}

	
}
