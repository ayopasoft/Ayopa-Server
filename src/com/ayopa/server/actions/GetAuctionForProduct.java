package com.ayopa.server.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.ayopa.server.model.Auction;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage (value="application")
@Results({
	@Result( name=Action.SUCCESS, type="json",   params={ "root", "auction_container" } )
})
public class GetAuctionForProduct extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private String merchantID;
	private String productID;

	private Map<String, Object> auction;
	private Map<String, List<Map<String,Object>>> auction_schedule;
	private Map<String, Object> schedule_row;
	private HashMap<String, Map<String, Object>> auction_container;
	
	public void setMerchantID(String merchantID) {
		this.merchantID = merchantID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}
	
	public HashMap<String, Map<String, Object>> getAuction_container() {
		return auction_container;
	}

	@Override
	public String execute() throws Exception {
		
		Auction myAuction = new Auction();
		merchantID = "1";
		productID = "4";
		
		String jsonString = myAuction.getAuctionForProduct(merchantID, productID);
		
		System.out.println(jsonString);
		
		auction = new HashMap<String, Object> ();
		auction_schedule = new HashMap<String, List<Map<String,Object>>>();
		schedule_row = new HashMap<String, Object>();
		auction_container = new HashMap<String, Map<String,Object>>();
		
		auction.put("auction_id", 31544381);
		auction.put("product_name", "42\" LCD HDTV");
		auction.put("product_descr", "High-definition Television (HDTV) makes any room in your home come alive. Enjoy the latest in DVDs, HD digital programming, and next generation gaming, all in vivid, jaw-dropping detail. LG Full 1080p makes ordinary television feel like a fuzzy, low contrast memory. With a fully digital picture and fully digital sound at the highest resolution available, your screen will produce pictures so crisp, so lifelike that you\'re no longer watching TV; you\'re truly experiencing it.");
		auction.put("auction_start", "03/16/2011 10:00 am");
		auction.put("auction_end", "03/31/2011 10:00 am");
		auction.put("auction_maxunits", "100");
		auction.put("auction_startprice", "900");
		auction.put("auction_image", "http://www.ayopadev.com/mm5/graphics/00000001/313PpMMkZWL._SL500_AA300_th.jpg");
		auction.put("product_link", "http://www.ayopadev.com/product/HJS-TV1.html");
		auction.put("auction_category", "Electronics");
		auction.put("pricing_conflict", 700);
		
	
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>(); 
		
		schedule_row.put("discount", 100);
		schedule_row.put("min", 20);
		schedule_row.put("max", 70);
		schedule_row.put("add", "");
		
		list.add(schedule_row);
		
		
		
		schedule_row = new HashMap<String, Object>();
		schedule_row.put("discount", 100);
		schedule_row.put("min", 71);
		schedule_row.put("max", 90);
		schedule_row.put("add", "");
		
		
		list.add(schedule_row);
		
		schedule_row = new HashMap<String, Object>();
		schedule_row.put("discount", 100);
		schedule_row.put("min", 91);
		schedule_row.put("max", 100);
		schedule_row.put("add", "");
		
		list.add(schedule_row);
		
		auction_schedule.put("schedule_row", list);
		
		auction.put("auction_schedule", auction_schedule);
		
		auction_container.put("auction", auction);
		
		return Action.SUCCESS;
	}
	
}
