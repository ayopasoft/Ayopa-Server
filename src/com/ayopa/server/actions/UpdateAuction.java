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
public class UpdateAuction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private String auctionDef;

	private Map<String, Integer> auction;

	
	public void setAuctionDef(String auctionDef) {
		this.auctionDef = auctionDef;
	}

	public Map<String, Integer> getAuction () {
		return auction;
	}
	
	@Override
	public String execute() throws Exception {
		auction = new HashMap<String, Integer> ();
		auction.put("auction_id", 123456);
		
		return Action.SUCCESS;
	}
	
}
