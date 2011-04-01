package com.ayopa.server.actions.miva;


import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.ayopa.server.model.Purchase;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS, type="string", params={ "contentType", "text/plain", "property", "success" } ),

})
public class SetAuctionParticipation extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private String auction_id;
	private String buyer_id;
	private Integer quantity;
	private String success;
	

	public String getSuccess() {
		return success;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public void setAuction_id(String auction_id) {
		this.auction_id = auction_id;
	}

	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}


	@Override
	public String execute() throws Exception {
		
		success = Purchase.putPurchase(auction_id, buyer_id, quantity);
		
		return Action.SUCCESS;
	}

	
}
