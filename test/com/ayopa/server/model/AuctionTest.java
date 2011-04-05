package com.ayopa.server.model;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class AuctionTest {
	private static Log log = LogFactory.getLog(AuctionTest.class);
	
	@Test
	public void testPutAuction() {
		//fail("Not yet implemented");
	}
	
	@Test
	public void testAuctionToJson(){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
		Auction auction = new Auction();
		List<ScheduleItem> list = new ArrayList<ScheduleItem>();
		String strInput = "[{\"min\":20,\"max\":25,\"dis\":5,\"add\":5},{\"min\":30,\"max\":35,\"dis\":6,\"add\":3},{\"min\":40,\"max\":45,\"dis\":7.9,\"add\":4}]";
		list = ScheduleSerializer.toSchedule(strInput);
		String result = "";
		
		result = auction.auctionToJson(auction);
		//assertEquals("Empty Auction definition to JSON failed","[]",result);
		
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
		auction.setAuction_startprice(25.00);
		auction.setProduct_category("Electronics");
		auction.setAuction_priceconflict(5.00);
		auction.setMerchant_id("1");
		auction.setMerchant_name("Happy Jack Software");
		auction.setMerchant_website("Merchant Website");
		auction.setAuction_highlighted(Boolean.TRUE);
	
		auction.setAuction_schedule(list);
		
		result = auction.auctionToJson(auction);
		
		assertEquals("Full Auction definition to JSON failed","{\"auction\":{\"merchant_website\":\"Merchant Website\",\"auction_highlighted\":true,\"product_id\":\"4\",\"merchant_name\":\"Happy Jack Software\",\"product_name\":\"Product Title\",\"auction_maxunits\":100,\"auction_end\":\"2011-03-29\",\"auction_deleted\":null,\"auction_id\":\"12345\",\"auction_startprice\":25,\"product_category\":\"Electronics\",\"auction_start\":\"2011-03-28\",\"auction_schedule\":{\"schedule_row\":[{\"min\":20,\"max\":25,\"dis\":5,\"add\":5},{\"min\":30,\"max\":35,\"dis\":6,\"add\":3},{\"min\":40,\"max\":45,\"dis\":7.9,\"add\":4}]},\"product_image\":\"Product Image\",\"product_descr\":\"Product Description\",\"pricing_conflict\":5,\"auction_ended\":null,\"merchant_id\":\"1\",\"product_link\":\"Product URL\"}}",result);
	}

	@Test
	public void testJsonToAuction() throws ParseException{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Auction auction = new Auction();
		String jsonString = "{\"auction\":{\"merchant_website\":\"Merchant Website\",\"auction_highlighted\":true,\"product_id\":\"4\",\"merchant_name\":\"Happy Jack Software\",\"product_name\":\"Product Title\",\"auction_maxunits\":100,\"auction_end\":\"2011-03-29\",\"auction_deleted\":null,\"auction_id\":\"12345\",\"auction_startprice\":25,\"product_category\":\"Electronics\",\"auction_start\":\"2011-03-28\",\"auction_schedule\":{\"schedule_row\":[{\"min\":20,\"max\":25,\"dis\":5,\"add\":5},{\"min\":30,\"max\":35,\"dis\":6,\"add\":3},{\"min\":40,\"max\":45,\"dis\":7.9,\"add\":4}]},\"product_image\":\"Product Image\",\"product_descr\":\"Product Description\",\"pricing_conflict\":5,\"auction_ended\":null,\"merchant_id\":\"1\",\"product_link\":\"Product URL\"}}";
		auction = auction.jsonToAuction(jsonString);
		
		assertEquals("Json to Auction failed for full definition: auction_id","12345", auction.getAuction_id());
		assertEquals("Json to Auction failed for full definition: schedule", "[{\"min\":20,\"max\":25,\"dis\":5,\"add\":5},{\"min\":30,\"max\":35,\"dis\":6,\"add\":3},{\"min\":40,\"max\":45,\"dis\":7.9,\"add\":4}]" , ScheduleSerializer.toJson(auction.getAuction_schedule()));
	    assertEquals("Json to Auction faild for full definition: auction_end",df.parse("2011-03-29"),auction.getAuction_end());
	}
	
	@Test
	public void testGetAuctionsForBuyer() throws IOException
	{
		List<AuctionDTO> list = new ArrayList<AuctionDTO>();
		Auction auction = new Auction();
		String buyer_id = "556659695";
		list = auction.getAuctionsForBuyer(buyer_id);
		
		assertEquals("List size not what expected", 1, list.size());
		
		System.out.println(list);
		
		
	}
	
}
