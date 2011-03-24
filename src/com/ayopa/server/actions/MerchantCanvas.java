package com.ayopa.server.actions;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.ayopa.server.utils.Auction;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS, location="MerchantCanvas.jsp" ),

})
public class MerchantCanvas extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private List<Auction> auctions;
	//define getters for data elements
	
	private List<Auction> highlightedAuctions;
	
	
	public List<Auction> getAuctions() {
		return auctions;
	}
	
	
	public List<Auction> getHighlightedAuctions() {
		return highlightedAuctions;
	}


	private List<Auction> getHighAuctions() {
		//return highlighted auctions
		List<Auction> highlightedAuctions = new ArrayList<Auction>();
		return highlightedAuctions;
	}
	
	
	private List<Auction> getMerchantAuctions() {
		//return non-highlighted auctions
		List<Auction> merchantAuctions = new ArrayList<Auction>();
		return merchantAuctions;
	}

	@Override
	public String execute() throws Exception {
	
		
		Auction singleAuction = new Auction();
		Auction singleAuction2 = new Auction();
		
		auctions = new ArrayList<Auction>();
		highlightedAuctions = new ArrayList<Auction>();
		
		singleAuction.setTitle("42\" LCD HDTV");
		singleAuction.setLink("http://www.ayopadev.com/product/HJS-TV1.html");
		singleAuction.setImage("http://www.ayopadev.com/mm5/graphics/00000001/313PpMMkZWL._SL500_AA300_th.jpg");
		singleAuction.setStart_price(900.00);
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
