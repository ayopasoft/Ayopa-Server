package com.ayopa.server.actions.miva;


import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.ayopa.server.model.Purchase;
import com.ayopa.server.utils.FBUtils;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS, type="string", params={ "contentType", "text/plain", "property", "success" } ),

})
public class SetAuctionParticipation extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private String auctionID;
	private String buyerID;
	private Integer quantity;
	private double price;
	
	private String success;
	
	
	

	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getSuccess() {
		return success;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public void setAuctionID(String auctionID) {
		this.auctionID = auctionID;
	}


	public void setBuyerID(String buyerID) {
		this.buyerID = buyerID;
	}


	@Override
	public String execute() throws Exception {
		
		success = Purchase.putPurchase(auctionID, buyerID, quantity, price);
		
		FBUtils.postAuctionToFacebook(auctionID, buyerID);
		
		return Action.SUCCESS;
	}

	
}
