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
	@Result( name=Action.SUCCESS, location="ConsumerAuctions.jsp" ),

})
public class ConsumerAuctions extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 1L;
	
	private String buyer_id;
	private Map<String, Object> session;
	private List<AuctionDTO> auctions;
	private List<String> categories;
	private String category;
	
	

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public List<AuctionDTO> getAuctions() {
		return auctions;
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

	
	

	@Override
	public String execute() throws Exception {
				
		Auction auction = new Auction();
		if (category == null || category.equals(""))
			auctions = AuctionDTO.auctionsToAuctionDTO(auction.getCurrentAuctions());
		else
			auctions = AuctionDTO.auctionsToAuctionDTO(auction.getCurrentAuctions(category));
		
		categories = auction.getCurrentCategories();
			
		return Action.SUCCESS;
	}



	
}
