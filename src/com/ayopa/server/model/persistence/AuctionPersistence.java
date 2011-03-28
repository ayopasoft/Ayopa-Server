package com.ayopa.server.model.persistence;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ayopa.server.model.Auction;
import com.ayopa.server.model.ScheduleSerializer;
import com.ayopa.server.utils.AwsFacade;

public class AuctionPersistence {
	private static Log log = LogFactory.getLog(AuctionPersistence.class);
	
	public String putAuction (Auction auction) throws IOException {
		if (auction.getAuction_id() == null || auction.getAuction_id().length() == 0)
			auction.setAuction_id(UUID.randomUUID().toString());
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
		
		
		Map<String, String> map = new HashMap<String, String>();
		map.put(AwsFacade.Key.AUCTION_ID,auction.getAuction_id());
		map.put(AwsFacade.Key.PRODUCT_ID, auction.getProduct_id());
		map.put(AwsFacade.Key.PRODUCT_NAME, auction.getProduct_title());
		map.put(AwsFacade.Key.PRODUCT_DESCR, auction.getProduct_description());
		map.put(AwsFacade.Key.PRODUCT_CAT, auction.getProduct_category());
		map.put(AwsFacade.Key.PRODUCT_IMAGE_URL, auction.getProduct_image());
		map.put(AwsFacade.Key.PRODUCT_URL, auction.getProduct_url());
		map.put(AwsFacade.Key.AUCTION_START, df.format(auction.getAuction_start()));
		map.put(AwsFacade.Key.AUCTION_END, df.format(auction.getAuction_end()));
		map.put(AwsFacade.Key.AUCTION_MAXUNITS, Integer.toString(auction.getAuction_maxunits()));
		map.put(AwsFacade.Key.AUCTION_HIGHLIGHTED, Boolean.toString(auction.getAuction_highlighted()));
		map.put(AwsFacade.Key.AUCTION_SCHEDULE, ScheduleSerializer.toJson(auction.getAuction_schedule()));
		map.put(AwsFacade.Key.AUCTION_STARTPRICE, Double.toString(auction.getAuction_startprice()));
		map.put(AwsFacade.Key.AUCTION_PRICECONFLICT, Double.toString(auction.getAuction_priceconflict()));
		map.put(AwsFacade.Key.MERCHANT_ID, auction.getMerchant_id());
		map.put(AwsFacade.Key.MERCHANT_NAME, auction.getMerchant_name());
		map.put(AwsFacade.Key.MERCHANT_WEBSITE, auction.getMerchant_website());
		
		AwsFacade aws = AwsFacade.getInstance();
		aws.putRow(AwsFacade.Table.AUCTION, auction.getAuction_id(), map);
		
		return auction.getAuction_id();
		
	}
	
	public Auction getAuction (String auction_id) throws IOException {
		AwsFacade aws = AwsFacade.getInstance();
		
		Map<String,String> map = aws.getRow(AwsFacade.Table.AUCTION, auction_id);
		
		Auction auction = mapToAuction(map);
		
		return auction;
	}

	public Auction mapToAuction(Map<String, String> map) {
		Auction auction = new Auction ();
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
		
		auction.setAuction_id (map.get(AwsFacade.Key.AUCTION_ID));
		auction.setProduct_id(map.get(AwsFacade.Key.PRODUCT_ID));
		auction.setProduct_title(map.get(AwsFacade.Key.PRODUCT_NAME));
		auction.setProduct_description(map.get(AwsFacade.Key.PRODUCT_DESCR));
		auction.setProduct_category(map.get(AwsFacade.Key.PRODUCT_CAT));
		auction.setProduct_image(map.get(AwsFacade.Key.PRODUCT_IMAGE_URL));
		auction.setProduct_url(map.get(AwsFacade.Key.PRODUCT_URL));
		try {
			auction.setAuction_start(df.parse(map.get(AwsFacade.Key.AUCTION_START)));
			auction.setAuction_end(df.parse(map.get(AwsFacade.Key.AUCTION_END)));
		} catch (ParseException e) {
			log.error("Got bad date format from Amazon table: " + map.get(AwsFacade.Key.AUCTION_START) + 
					" or " + map.get(AwsFacade.Key.AUCTION_END), e);
		}
		auction.setAuction_highlighted(Boolean.parseBoolean(map.get(AwsFacade.Key.AUCTION_HIGHLIGHTED)));
		auction.setAuction_maxunits(Integer.parseInt(map.get(AwsFacade.Key.AUCTION_MAXUNITS)));
		auction.setAuction_startprice(Double.parseDouble(map.get(AwsFacade.Key.AUCTION_STARTPRICE)));
		auction.setAuction_priceconflict(Double.parseDouble(map.get(AwsFacade.Key.AUCTION_PRICECONFLICT)));
		auction.setAuction_schedule(ScheduleSerializer.toSchedule(map.get(AwsFacade.Key.AUCTION_SCHEDULE)));
		auction.setMerchant_id(map.get(AwsFacade.Key.MERCHANT_ID));
		auction.setMerchant_name(map.get(AwsFacade.Key.MERCHANT_NAME));
		auction.setMerchant_website(map.get(AwsFacade.Key.MERCHANT_WEBSITE));
		return auction;
	}
}
