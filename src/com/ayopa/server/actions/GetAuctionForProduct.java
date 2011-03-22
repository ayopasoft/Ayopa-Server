package com.ayopa.server.actions;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS, type="json",   params={ "root", "auction" } )
})
public class GetAuctionForProduct extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private String merchantID;
	private String productID;

	private Map auction;
	
	public void setMerchantID(String merchantID) {
		this.merchantID = merchantID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public Map getAuction () {
		return auction;
	}
	
	@Override
	public String execute() throws Exception {
		auction = new HashMap ();
		auction.put("id", 100);
		auction.put("merchant_id", merchantID);
		auction.put("product_id", productID);
		return Action.SUCCESS;
	}
}
