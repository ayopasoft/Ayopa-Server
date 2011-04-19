package com.ayopa.server.actions.miva;


import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.ayopa.server.model.Buyer;
import com.ayopa.server.model.Purchase;
import com.ayopa.server.utils.FBUtils;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS, type="string", params={ "contentType", "text/plain", "property", "purchaseID" } ),

})
public class SetAuctionParticipation extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private String auctionID;
	private String buyerID;
	private Integer quantity;
	private double price;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String country;
	private String zip;
	
	private String purchaseID;
	
	
	

	public String getAddress1() {
		return address1;
	}


	public void setAddress1(String address1) {
		this.address1 = address1;
	}


	public String getAddress2() {
		return address2;
	}


	public void setAddress2(String address2) {
		this.address2 = address2;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getZip() {
		return zip;
	}


	public void setZip(String zip) {
		this.zip = zip;
	}


	public Integer getQuantity() {
		return quantity;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	


	public String getPurchaseID() {
		return purchaseID;
	}


	public void setPurchaseID(String purchaseID) {
		this.purchaseID = purchaseID;
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
		
		try {
			purchaseID = Purchase.putPurchase(auctionID, buyerID, quantity, price);
			
			Buyer.addAddress(buyerID, address1, address2, city, state, zip, country);
		    
			FBUtils.postAuctionToFacebook(auctionID, buyerID);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			purchaseID = "";
		}
		
		return Action.SUCCESS;
	}

	
}
