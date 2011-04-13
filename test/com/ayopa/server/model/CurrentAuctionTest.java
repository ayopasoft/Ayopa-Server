package com.ayopa.server.model;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class CurrentAuctionTest {
	private static Log log = LogFactory.getLog(CurrentAuctionTest.class);
	
	@Test
	public void testGetCurrentQuantity() throws IOException {
		CurrentAuction currAuction = new CurrentAuction();
		int quantity = currAuction.getCurrentQuantity("518a13e1-8439-4aec-8ea9-d9e4b4f4ab4a");
		
		assertEquals("GetCurrentQuantity returned incorrect value",21,quantity,0);
		
	}
	
	
	@Test
	public void testGetAuctionTimeRemaining()
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd h:mm a"); 
		
		Auction auction = new Auction();
		try {
			auction.setAuction_start(df.parse("2011-03-28 10:00 am"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		try {
			auction.setAuction_end(df.parse("2011-04-15 10:00 am"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		
		Map<String, Long> map = new HashMap<String, Long>();
		
		map = CurrentAuction.getAuctionTimeRemaining(auction);
		
		assertEquals("Time remaining days wrong",2,map.get("days"),0);
		
	}
	
	@Test
	public void testGetCurrentAuctionInfo() {
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
		Auction auction = new Auction();
		List<ScheduleItem> list = new ArrayList<ScheduleItem>();
		
		auction.setAuction_id("12345");
		auction.setProduct_id("4");
		auction.setProduct_title("Product Title");
		auction.setProduct_description("Product Description");
		auction.setProduct_image("Product Image");
		auction.setProduct_url("Product URL");
		try {
			auction.setAuction_start(df.parse("2011-03-28"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		try {
			auction.setAuction_end(df.parse("2011-03-29"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		auction.setAuction_maxunits(100);
		auction.setAuction_startprice(100.00);
		auction.setProduct_category("Electronics");
		auction.setAuction_priceconflict(5.00);
		auction.setMerchant_id("1");
		auction.setMerchant_name("Happy Jack Software");
		auction.setMerchant_website("Merchant Website");
		auction.setAuction_highlighted(Boolean.TRUE);
	
		String strInput = "[{\"min\":5,\"max\":30,\"dis\":5,\"add\":5}]";
		list = ScheduleSerializer.toSchedule(strInput);
		auction.setAuction_schedule(list);
		
		CurrentAuction currAuction = new CurrentAuction();
		currAuction = CurrentAuction.getCurrentAuctionInfo(auction, 3);
		assertEquals("Current price computation for quantity below 1st min value failed",100,currAuction.getCurrent_price(),0);
		assertEquals("Current level computation for quantity below 1st min value failed",3,currAuction.getCurrent_level(),0);
		assertEquals("Next price computation for quantity below 1st min value failed",95,currAuction.getNext_price(),0);
		assertEquals("Next level computation for quantity below 1st min value failed",5,currAuction.getNext_level());
		assertEquals("Lowest price computation for quantity below 1st min value failed",70,currAuction.getLowest_price(),0);
		assertEquals("Lowest level computation for quantity below 1st min value failed",30,currAuction.getLowest_level());
		
		
		strInput = "[{\"min\":10,\"max\":20,\"dis\":5,\"add\":5},{\"min\":30,\"max\":50,\"dis\":25,\"add\":0}]";
		list.clear();
		list = ScheduleSerializer.toSchedule(strInput);
		auction.setAuction_schedule(list);
		
		currAuction = new CurrentAuction();
		currAuction = CurrentAuction.getCurrentAuctionInfo(auction, 3);
		
		assertEquals("Current price computation for quantity below 1st min value failed",100,currAuction.getCurrent_price(),0);
		assertEquals("Current level computation for quantity below 1st min value failed",3,currAuction.getCurrent_level(),0);
		assertEquals("Next price computation for quantity below 1st min value failed",95,currAuction.getNext_price(),0);
		assertEquals("Next level computation for quantity below 1st min value failed",10,currAuction.getNext_level());
		assertEquals("Lowest price computation for quantity below 1st min value failed",75,currAuction.getLowest_price(),0);
		assertEquals("Lowest level computation for quantity below 1st min value failed",30,currAuction.getLowest_level());
		
		currAuction = CurrentAuction.getCurrentAuctionInfo(auction, 10);
		
		assertEquals("Current price computation for quantity in add range - min value failed",95,currAuction.getCurrent_price(),0);
		assertEquals("Current level computation for quantity in add range - min failed",10,currAuction.getCurrent_level(),0);
		assertEquals("Next price level computation for quantity in add range - min level failed",90,currAuction.getNext_price(),0);
		assertEquals("Next level computation for quantity in add range - min level failed",15,currAuction.getNext_level());
		assertEquals("Lowest price computation for quantity in add range - min level failed",75,currAuction.getLowest_price(),0);
		assertEquals("Lowest level computation for quantity in add range - min level failed",30,currAuction.getLowest_level());
		
		currAuction = CurrentAuction.getCurrentAuctionInfo(auction, 13);
		
		assertEquals("Current price computation for quantity in add range - 1st level failed",95,currAuction.getCurrent_price(),0);
		assertEquals("Current level computation for quantity in add range - 1st level failed",13,currAuction.getCurrent_level(),0);
		assertEquals("Next price level computation for quantity in add range - 1st level failed",90,currAuction.getNext_price(),0);
		assertEquals("Next level computation for quantity in add range - 1st level failed",15,currAuction.getNext_level());
		assertEquals("Lowest price computation for quantity in add range - 1st level failed",75,currAuction.getLowest_price(),0);
		assertEquals("Lowest level computation for quantity in add range - 1st level failed",30,currAuction.getLowest_level());
	
		
		currAuction = CurrentAuction.getCurrentAuctionInfo(auction, 15);
		assertEquals("Current price computation for quantity in add range - 2nd level failed",90,currAuction.getCurrent_price(),0);
		assertEquals("Current level computation for quantity in add range - 2nd level failed",15,currAuction.getCurrent_level(),0);
		assertEquals("Next price level computation for quantity in add range - 2nd level failed",85,currAuction.getNext_price(),0);
		assertEquals("Next level computation for quantity in add range - 2nd level failed",20,currAuction.getNext_level());
		assertEquals("Lowest price computation for quantity in add range - 2nd level failed",75,currAuction.getLowest_price(),0);
		assertEquals("Lowest level computation for quantity in add range - 1st level failed",30,currAuction.getLowest_level());
		
		currAuction = CurrentAuction.getCurrentAuctionInfo(auction, 20);
		assertEquals("Current price computation for quantity in add range - max value failed",85,currAuction.getCurrent_price(),0);
		assertEquals("Current level computation for quantity in add range - max value failed",20,currAuction.getCurrent_level(),0);
		assertEquals("Next price level computation for quantity in add range - max value level failed",75,currAuction.getNext_price(),0);
		assertEquals("Next level computation for quantity in add range - max value failed",30,currAuction.getNext_level());
		assertEquals("Lowest price computation for quantity in add range - value failed",75,currAuction.getLowest_price(),0);
		assertEquals("Lowest level computation for quantity in add range - value failed",30,currAuction.getLowest_level());
		
		currAuction = CurrentAuction.getCurrentAuctionInfo(auction, 22);
		assertEquals("Current price computation for quantity  - between levels failed",85,currAuction.getCurrent_price(),0);
		assertEquals("Current level computation for quantity  - between levels failed",22,currAuction.getCurrent_level(),0);
		assertEquals("Next price level computation for quantity  - between levels failed",75,currAuction.getNext_price(),0);
		assertEquals("Next level computation for quantity  - between levels failed",30,currAuction.getNext_level());
		assertEquals("Lowest price computation for quantity  - between levels failed",75,currAuction.getLowest_price(),0);
		assertEquals("Lowest level computation for quantity  - between levels failed",30,currAuction.getLowest_level());
		
		currAuction = CurrentAuction.getCurrentAuctionInfo(auction, 30);
		assertEquals("Current price computation for quantity static level min failed",75,currAuction.getCurrent_price(),0);
		assertEquals("Current level computation for quantity static level min failed",30,currAuction.getCurrent_level(),0);
		assertEquals("Next price level computation for quantity  static level min failed",75,currAuction.getNext_price(),0);
		assertEquals("Next level computation for quantity static level min failed",30,currAuction.getNext_level());
		assertEquals("Lowest price computation for quantity  static level min failed",75,currAuction.getLowest_price(),0);
		assertEquals("Lowest level computation for quantity static level min failed",30,currAuction.getLowest_level());
	
		currAuction = CurrentAuction.getCurrentAuctionInfo(auction, 45);
		assertEquals("Current price computation for quantity static level between failed",75,currAuction.getCurrent_price(),0);
		assertEquals("Current level computation for quantity static level between  failed",45,currAuction.getCurrent_level(),0);
		assertEquals("Next price level computation for quantity  static level between  failed",75,currAuction.getNext_price(),0);
		assertEquals("Next level computation for quantity static level between  failed",30,currAuction.getNext_level());
		assertEquals("Lowest price computation for quantity  static level between  failed",75,currAuction.getLowest_price(),0);
		assertEquals("Lowest level computation for quantity static level between  failed",30,currAuction.getLowest_level());
	
		currAuction = CurrentAuction.getCurrentAuctionInfo(auction, 50);
		assertEquals("Current price computation for quantity static level max failed",75,currAuction.getCurrent_price(),0);
		assertEquals("Current level computation for quantity static level max failed",50,currAuction.getCurrent_level(),0);
		assertEquals("Next price level computation for quantity  static level max failed",75,currAuction.getNext_price(),0);
		assertEquals("Next level computation for quantity static level max failed",30,currAuction.getNext_level());
		assertEquals("Lowest price computation for quantity  static level max failed",75,currAuction.getLowest_price(),0);
		assertEquals("Lowest level computation for quantity static level max failed",30,currAuction.getLowest_level());
	
		currAuction = CurrentAuction.getCurrentAuctionInfo(auction, 60);
		assertEquals("Current price computation for quantity beyond max failed",75,currAuction.getCurrent_price(),0);
		assertEquals("Current level computation for quantity beyond max failed",60,currAuction.getCurrent_level(),0);
		assertEquals("Next price level computation for quantity  beyond max failed",75,currAuction.getNext_price(),0);
		assertEquals("Next level computation for quantity static beyond failed",50,currAuction.getNext_level());
		assertEquals("Lowest price computation for quantity  beyond max failed",75,currAuction.getLowest_price(),0);
		assertEquals("Lowest level computation for quantity beyond max failed",30,currAuction.getLowest_level());
	
		
		//a little more complicated
		strInput = "[{\"min\":10,\"max\":20,\"dis\":5,\"add\":5},{\"min\":30,\"max\":50,\"dis\":25,\"add\":0},{\"min\":50,\"max\":60,\"dis\":5,\"add\":5}]";
		list.clear();
		list = ScheduleSerializer.toSchedule(strInput);
		auction.setAuction_schedule(list);
		
		currAuction = CurrentAuction.getCurrentAuctionInfo(auction, 55);
		assertEquals("Current price computation for quantity beyond max failed",65,currAuction.getCurrent_price(),0);
		assertEquals("Current level computation for quantity beyond max failed",55,currAuction.getCurrent_level(),0);
		assertEquals("Next price level computation for quantity  beyond max failed",60,currAuction.getNext_price(),0);
		assertEquals("Next level computation for quantity static beyond failed",60,currAuction.getNext_level());
		assertEquals("Lowest price computation for quantity  beyond max failed",60,currAuction.getLowest_price(),0);
		assertEquals("Lowest level computation for quantity beyond max failed",50,currAuction.getLowest_level());
	
		currAuction = CurrentAuction.getCurrentAuctionInfo(auction, 61);
		assertEquals("Current price computation for quantity beyond max failed",60,currAuction.getCurrent_price(),0);
		assertEquals("Current level computation for quantity beyond max failed",61,currAuction.getCurrent_level(),0);
		assertEquals("Next price level computation for quantity  beyond max failed",60,currAuction.getNext_price(),0);
		assertEquals("Next level computation for quantity static beyond failed",60,currAuction.getNext_level());
		assertEquals("Lowest price computation for quantity  beyond max failed",60,currAuction.getLowest_price(),0);
		assertEquals("Lowest level computation for quantity beyond max failed",50,currAuction.getLowest_level());
	
		
	
	}

}
