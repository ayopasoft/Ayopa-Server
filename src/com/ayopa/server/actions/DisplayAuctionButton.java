package com.ayopa.server.actions;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS, location="DisplayAuctionButton.jsp" ),
})
public class DisplayAuctionButton extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private String auctionID;

	
	public void setAuctionID(String auctionID) {
		this.auctionID = auctionID;
	}
	
	private float currentPrice;
	
	public float getCurrentPrice () {
		return currentPrice;
	}


	@Override
	public String execute() throws Exception {
		
		//detects pricing conflict
		
		currentPrice = 80;
		
		return Action.SUCCESS;
	}
	
}
