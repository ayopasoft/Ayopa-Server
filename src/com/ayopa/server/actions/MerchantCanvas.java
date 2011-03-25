package com.ayopa.server.actions;

import java.util.ArrayList;
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
	@Result( name=Action.SUCCESS, location="MerchantCanvas.jsp" ),

})
public class MerchantCanvas extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private List<AuctionDTO> auctions;
	//define getters for data elements
	
	private List<AuctionDTO> highlightedAuctions;
	
	
	public List<AuctionDTO> getAuctions() {
		return auctions;
	}
	
	
	public List<AuctionDTO> getHighlightedAuctions() {
		return highlightedAuctions;
	}


	private List<AuctionDTO> getHighAuctions() {
		//return highlighted auctions
		List<AuctionDTO> highlightedAuctions = new ArrayList<AuctionDTO>();
		return highlightedAuctions;
	}
	
	
	private List<AuctionDTO> getMerchantAuctions() {
		//return non-highlighted auctions
		List<AuctionDTO> merchantAuctions = new ArrayList<AuctionDTO>();
		return merchantAuctions;
	}

	@Override
	public String execute() throws Exception {
	
		Auction auction = new Auction();
		auction = auction.getAuction("204cff85-0796-4535-8764-9a7c356a2b12");
		
		AuctionDTO singleAuction = new AuctionDTO();
		AuctionDTO singleAuction2 = new AuctionDTO();
		
		auctions = new ArrayList<AuctionDTO>();
		highlightedAuctions = new ArrayList<AuctionDTO>();
		
		singleAuction.setTitle(auction.getProduct_title());
		singleAuction.setLink(auction.getProduct_url());
		singleAuction.setImage(auction.getProduct_image());
		singleAuction.setStart_price(auction.getAuction_startprice());
		singleAuction.setCurrent_price(800.00);
		singleAuction.setNext_price(700.00);
		singleAuction.setLowest_price(650.00);
		singleAuction.setStart_quant(20);
		singleAuction.setCurrent_quant(55);
		singleAuction.setNext_quant(71);
		singleAuction.setHighest_quant(91);
		singleAuction.setPurchase_price(900.00);
		singleAuction.setTime_seconds(38);
		singleAuction.setTime_minutes(45);
		singleAuction.setTime_hours(14);
		singleAuction.setTime_days(2);
		highlightedAuctions.add(singleAuction);
		
		singleAuction2.setTitle("42\" LCD HDTV");
		singleAuction2.setLink("http://www.ayopadev.com/product/HJS-TV1.html");
		singleAuction2.setImage("http://www.ayopadev.com/mm5/graphics/00000001/313PpMMkZWL._SL500_AA300_th.jpg");
		singleAuction2.setStart_price(900.00);
		singleAuction2.setCurrent_price(800.00);
		singleAuction2.setNext_price(700.00);
		singleAuction2.setLowest_price(650.00);
		singleAuction2.setStart_quant(20);
		singleAuction2.setCurrent_quant(55);
		singleAuction2.setNext_quant(71);
		singleAuction2.setHighest_quant(91);
		singleAuction2.setPurchase_price(900.00);
		singleAuction2.setTime_seconds(38);
		singleAuction2.setTime_minutes(45);
		singleAuction2.setTime_hours(14);
		singleAuction2.setTime_days(2);
		auctions.add(singleAuction2);
		
		return Action.SUCCESS;
	}



	
}
