package com.ayopa.server.model;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ayopa.server.model.persistence.AuctionPersistence;
import com.ayopa.server.utils.AwsFacade;

public class Auction {
	
	private static Log log = LogFactory.getLog(AuctionPersistence.class);
	
	private String auction_id;
	private String product_id;
	private String product_title;
	private String product_description;
	private String product_category;
	private String product_image;
	private String product_url;
	private Date auction_start;
	private Date auction_end;
	private boolean auction_highlighted;
	private int auction_maxunits;
	private double auction_startprice;
	private double auction_priceconflict;
	private List<ScheduleItem> auction_schedule;
	private String merchant_id;
	private String merchant_name;
	private String merchant_website;
	
	
	public String getAuction_id() {
		return auction_id;
	}
	public void setAuction_id(String auction_id) {
		this.auction_id = auction_id;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getProduct_title() {
		return product_title;
	}
	public void setProduct_title(String product_title) {
		this.product_title = product_title;
	}
	public String getProduct_description() {
		return product_description;
	}
	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}
	public String getProduct_category() {
		return product_category;
	}
	public void setProduct_category(String product_category) {
		this.product_category = product_category;
	}
	public String getProduct_image() {
		return product_image;
	}
	public void setProduct_image(String product_image) {
		this.product_image = product_image;
	}
	public String getProduct_url() {
		return product_url;
	}
	public void setProduct_url(String product_url) {
		this.product_url = product_url;
	}
	public Date getAuction_start() {
		return auction_start;
	}
	public void setAuction_start(Date auction_start) {
		this.auction_start = auction_start;
	}
	public Date getAuction_end() {
		return auction_end;
	}
	public void setAuction_end(Date auction_end) {
		this.auction_end = auction_end;
	}
	public boolean getAuction_highlighted() {
		return auction_highlighted;
	}
	public void setAuction_highlighted(boolean auction_highlighted) {
		this.auction_highlighted = auction_highlighted;
	}
	public int getAuction_maxunits() {
		return auction_maxunits;
	}
	public void setAuction_maxunits(int auction_maxunits) {
		this.auction_maxunits = auction_maxunits;
	}
	public double getAuction_startprice() {
		return auction_startprice;
	}
	public void setAuction_startprice(double auction_startprice) {
		this.auction_startprice = auction_startprice;
	}
	public double getAuction_priceconflict() {
		return auction_priceconflict;
	}
	public void setAuction_priceconflict(double auction_priceconflict) {
		this.auction_priceconflict = auction_priceconflict;
	}
	
	public List<ScheduleItem> getAuction_schedule() {
		return auction_schedule;
	}
	
	public void setAuction_schedule(List<ScheduleItem> auction_schedule) {
		this.auction_schedule = auction_schedule;
	}
	
	public String getMerchant_id() {
		return merchant_id;
	}
	public void setMerchant_id(String merchant_id) {
		this.merchant_id = merchant_id;
	}
	public String getMerchant_name() {
		return merchant_name;
	}
	public void setMerchant_name(String merchant_name) {
		this.merchant_name = merchant_name;
	}

	public String getMerchant_website() {
		return merchant_website;
	}
	public void setMerchant_website(String merchant_website) {
		this.merchant_website = merchant_website;
	}
	
	
	public Auction getAuction(String auction_id) throws IOException {
		
		Auction auction = new Auction();
		AuctionPersistence awsAuction = new AuctionPersistence();
		
		auction = awsAuction.getAuction(auction_id);
		return auction;
	}
	
	public String getAuctionForProduct (String merchant_id, String product_id) throws IOException{
		Auction auction = new Auction();
		AuctionPersistence ap = new AuctionPersistence();
		
		AwsFacade aws = AwsFacade.getInstance();
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
		
		String now = df.format(Calendar.getInstance().getTime());
	
	
		String query = "select * from `" + AwsFacade.Table.AUCTION + "` where `" + AwsFacade.Key.MERCHANT_ID + "` = '" 
			+ merchant_id + "' and `" + AwsFacade.Key.PRODUCT_ID + "` = '" + product_id + "' and `" + AwsFacade.Key.AUCTION_START + "` <= '" + now + "' and `" + AwsFacade.Key.AUCTION_END + "` >= '" + now + "'";
		
		log.info(query);
		
		List<Map<String,String>> results = aws.selectRows(query);
		
		
		if (results.size() == 0) {
			return "0";
		}
		else {
			if (results.size() > 1){
				log.info("getAuctionForProduct returned more than 1 result");
			}
			
			auction = ap.mapToAuction(results.get(0));
			
		}
		
			return auction.auction_id;
			
	}
	
	
	public String putAuction(String auctionDef) throws IOException {
		Auction auction = new Auction();
		AuctionPersistence awsAuction = new AuctionPersistence();
		
		
		//JSONObject jsonAuction = (JSONObject) JSONSerializer.toJSON( auctionDef ); 
		
		//jsonAuction.get("auction_id");
		
		/*Iterator<String> nameItr = jsonAuction.keys();
		HashMap<String, Object> outMap = new HashMap<String, Object>();
		while(nameItr.hasNext()) {
			String name = nameItr.next();
		    outMap.put(name, jsonAuction.getString(name));
		    
		}*/
		
		//populate auction object
		//putAuction
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
		
		auction.setProduct_id("4");
		auction.setProduct_title("42\" LCD HDTV");
		auction.setProduct_description("description");
		auction.setProduct_category("Electronics");
		auction.setProduct_image("http://www.ayopadev.com/mm5/graphics/00000001/313PpMMkZWL._SL500_AA300_th.jpg");
		auction.setProduct_url("http://www.ayopadev.com/product/HJS-TV1.html");
		try {
			auction.setAuction_start(df.parse("2011-03-24"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			log.error("Got bad date format from json String: " , e);
		}
		try {
			auction.setAuction_end(df.parse("2011-03-29"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			log.error("Got bad date format from json String: " , e);
		}
		auction.setAuction_highlighted(Boolean.TRUE);
		auction.setAuction_maxunits(100);
		auction.setAuction_startprice(900.00);
		auction.setAuction_priceconflict(690.00);
		
		List<ScheduleItem> schedule = new ArrayList<ScheduleItem>();
		ScheduleItem item = new ScheduleItem();
		
		item.setDis(5.00);
		item.setMin(5);
		item.setMax(20);
		item.setAdd(2);
		
		schedule.add(item);
		
		auction.setAuction_schedule(schedule);
		auction.setMerchant_id("1");
		auction.setMerchant_name("Happy Jack Software");
		auction.setMerchant_website("http://www.ayopadev.com/mm5");
		
		String auctionReturn = awsAuction.putAuction(auction);

		
		Map<String,String> mapReturn = new HashMap<String, String> ();
		mapReturn.put("auction_id", auctionReturn);
		
		JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON( mapReturn );
		
		
		return jsonObject.toString();
	}
	
}
