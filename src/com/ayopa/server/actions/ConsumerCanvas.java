package com.ayopa.server.actions;

import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.SessionAware;

import com.ayopa.server.model.Auction;
import com.ayopa.server.model.AuctionDTO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS, location="ConsumerCanvas.jsp" ),

})
public class ConsumerCanvas extends ActionSupport implements SessionAware{
	private static final long serialVersionUID = 1L;
	
	private String buyer_id;
	private Map<String, Object> session;
	private boolean session_expired;
	
	

	
	public boolean isSession_expired() {
		return session_expired;
	}

	public void setSession_expired(boolean session_expired) {
		this.session_expired = session_expired;
	}

	public void setSession(Map<String, Object> map) {
		this.session = map;
	}
	
	public String getBuyer_id() {
		return buyer_id;
	}

	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}

	private List<AuctionDTO> auctions;
	//define getters for data elements
	
	public List<AuctionDTO> getAuctions() {
		return auctions;
	}
	
	

	@Override
	public String execute() throws Exception {
		//get users current auctions
		//authenticate by FB ID
		
		Auction auction = new Auction();
		
		if (buyer_id == null || buyer_id.equals("")) {
			if (session.containsKey("buyer_id")){
				buyer_id = (String) session.get("buyer_id");
				auctions = auction.getAuctionsForBuyer(buyer_id);
			}
			else
			{
				session_expired = Boolean.TRUE;
				
			}
		}
		else
		{
			session.put("buyer_id", buyer_id);
		}
		
		
		
		
		return Action.SUCCESS;
	}



	
}
