package com.ayopa.server.actions;

import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.ayopa.server.model.Auction;
import com.ayopa.server.model.AuctionDTO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS, location="AyopaAuctions.jsp" ),

})
public class AyopaAuctions extends ActionSupport {
private static final long serialVersionUID = 1L;
	
	private String buyer_id;
	private List<String> categories;
	private String category;
	
	
	
	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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
		
		if (category == null || category.equals(""))
			auctions = AuctionDTO.auctionsToAuctionDTO(auction.getCurrentAuctions());
		else
			auctions = AuctionDTO.auctionsToAuctionDTO(auction.getCurrentAuctions(category));
		
		
		categories = auction.getCurrentCategories();
		
		return Action.SUCCESS;
	}




	
}
