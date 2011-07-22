package com.ayopa.server.model.persistence;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ayopa.server.model.Auction;
import com.ayopa.server.model.ScheduleSerializer;
import com.ayopa.server.utils.AwsFacade;

public class AuctionPersistence {
	private static Log log = LogFactory.getLog(AuctionPersistence.class);
	
	public String putAuction (Auction auction) throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		
		if (auction.getAuction_id() == null || auction.getAuction_id().length() == 0) {
			auction.setAuction_id(UUID.randomUUID().toString());
			map.put(AwsFacade.Key.INVOICE_SENT, "0");
		}
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz"); 
		df.setTimeZone(TimeZone.getTimeZone("US/Mountain"));
		
		AwsFacade aws = AwsFacade.getInstance();
		
		
		//write product image to AWS bucket
		
		URL url = new URL(auction.getProduct_image());
	    BufferedImage image = ImageIO.read(url);
	    File file = new File(auction.getAuction_id() + ".jpg");
	    ImageIO.write(image, "jpg", file);
		
	    aws.createBucket(AwsFacade.Bucket.PRODUCT_IMAGES);
	    aws.writeFileToS3(AwsFacade.Bucket.PRODUCT_IMAGES, file.getName(), file);
	    aws.makePublic(AwsFacade.Bucket.PRODUCT_IMAGES, file.getName());
	    
	    auction.setProduct_image("http://" + AwsFacade.Bucket.PRODUCT_IMAGES + ".s3.amazonaws.com/"  + file.getName());
	    file.delete();
		
		
		map.put(AwsFacade.Key.AUCTION_ID,auction.getAuction_id());
		
		if (auction.getProduct_id() != null)
			map.put(AwsFacade.Key.PRODUCT_ID, auction.getProduct_id());
		if (auction.getProduct_title() != null)
			map.put(AwsFacade.Key.PRODUCT_NAME, auction.getProduct_title());
		if (auction.getProduct_description() != null)
			map.put(AwsFacade.Key.PRODUCT_DESCR, auction.getProduct_description());
		if (auction.getProduct_category() != null)
			map.put(AwsFacade.Key.PRODUCT_CAT, auction.getProduct_category());
		if (auction.getProduct_image() != null)
			map.put(AwsFacade.Key.PRODUCT_IMAGE_URL, auction.getProduct_image());
		if (auction.getProduct_url() != null)
			map.put(AwsFacade.Key.PRODUCT_URL, auction.getProduct_url());
		if (auction.getAuction_start() != null)
			map.put(AwsFacade.Key.AUCTION_START, df.format(auction.getAuction_start()));
		if (auction.getAuction_end() != null)
			map.put(AwsFacade.Key.AUCTION_END, df.format(auction.getAuction_end()));
		if (auction.getAuction_maxunits() >= 0)
			map.put(AwsFacade.Key.AUCTION_MAXUNITS, Integer.toString(auction.getAuction_maxunits()));
		if (auction.getAuction_highlighted() == true || auction.getAuction_highlighted() == false)
			map.put(AwsFacade.Key.AUCTION_HIGHLIGHTED, Boolean.toString(auction.getAuction_highlighted()));
		if (auction.getAuction_schedule() != null)
			map.put(AwsFacade.Key.AUCTION_SCHEDULE, ScheduleSerializer.toJson(auction.getAuction_schedule()));
		if (auction.getAuction_startprice() >= 0)
			map.put(AwsFacade.Key.AUCTION_STARTPRICE, Double.toString(auction.getAuction_startprice()));
		if (auction.getAuction_priceconflict() >= 0)
			map.put(AwsFacade.Key.AUCTION_PRICECONFLICT, Double.toString(auction.getAuction_priceconflict()));
		if (auction.getMerchant_website() != null)
			map.put(AwsFacade.Key.AUCTION_ENDED, auction.getMerchant_website());
		if (auction.getMerchant_id() != null)
			map.put(AwsFacade.Key.MERCHANT_ID, auction.getMerchant_id());
		if (auction.getMerchant_name() != null)
			map.put(AwsFacade.Key.MERCHANT_NAME, auction.getMerchant_name());
		if (auction.getMerchant_website() != null)
			map.put(AwsFacade.Key.MERCHANT_WEBSITE, auction.getMerchant_website());
		if (auction.getAuction_ended() != null)
			map.put(AwsFacade.Key.AUCTION_ENDED, auction.getAuction_ended());
		if (auction.getAuction_deleted() != null)
			map.put(AwsFacade.Key.AUCTION_DELETED, auction.getAuction_deleted());
		if (auction.getMerchant_fb_page() != null)
			map.put(AwsFacade.Key.MERCHANT_FB_PAGE, auction.getMerchant_fb_page());
		if (auction.getRebate_sent() != null)
			map.put(AwsFacade.Key.REBATE_SENT, auction.getRebate_sent());
		if (auction.getAuction_cleared() != null)
			map.put(AwsFacade.Key.AUCTION_CLEARED, auction.getAuction_cleared());
		if (auction.getInvoice_sent() != null)
			map.put(AwsFacade.Key.INVOICE_SENT, auction.getInvoice_sent());
		
		aws.putRow(AwsFacade.Table.AUCTION, auction.getAuction_id(), map);
		
		return auction.getAuction_id();
		
	}
	
	public void putAuctionCleared (String auction_id) throws IOException{
		Map<String, String> map = new HashMap<String, String>();
		AwsFacade aws = AwsFacade.getInstance();
		
		map.put(AwsFacade.Key.AUCTION_ID,auction_id);
		map.put(AwsFacade.Key.AUCTION_CLEARED, "1");
		
		aws.putRow(AwsFacade.Table.AUCTION, auction_id, map);
	}
	
	public static void putAttribute (String auction_id, String attribute, String value) throws IOException
	{
		Map<String, String> map = new HashMap<String, String>();
		AwsFacade aws = AwsFacade.getInstance();
		
		map.put(AwsFacade.Key.AUCTION_ID,auction_id);
		map.put(attribute, value);
		
		aws.putRow(AwsFacade.Table.AUCTION, auction_id, map);
	}
	
	
	public Auction getAuction (String auction_id) throws IOException {
		AwsFacade aws = AwsFacade.getInstance();
		
		Map<String,String> map = aws.getRow(AwsFacade.Table.AUCTION, auction_id);
		
		Auction auction = mapToAuction(map);
		
		return auction;
	}

	public Auction mapToAuction(Map<String, String> map) {
		Auction auction = new Auction ();
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz"); 
		df.setTimeZone(TimeZone.getTimeZone("US/Mountain"));
		
		if (map.containsKey(AwsFacade.Key.AUCTION_ID))
			auction.setAuction_id (map.get(AwsFacade.Key.AUCTION_ID));
		if (map.containsKey(AwsFacade.Key.PRODUCT_ID))
			auction.setProduct_id(map.get(AwsFacade.Key.PRODUCT_ID));
		if (map.containsKey(AwsFacade.Key.PRODUCT_NAME))
			auction.setProduct_title(map.get(AwsFacade.Key.PRODUCT_NAME));
		if (map.containsKey(AwsFacade.Key.PRODUCT_DESCR))
			auction.setProduct_description(map.get(AwsFacade.Key.PRODUCT_DESCR));
		if (map.containsKey(AwsFacade.Key.PRODUCT_CAT))
			auction.setProduct_category(map.get(AwsFacade.Key.PRODUCT_CAT));
		if (map.containsKey(AwsFacade.Key.PRODUCT_IMAGE_URL))
			auction.setProduct_image(map.get(AwsFacade.Key.PRODUCT_IMAGE_URL));
		if (map.containsKey(AwsFacade.Key.PRODUCT_URL))
			auction.setProduct_url(map.get(AwsFacade.Key.PRODUCT_URL));
		
		try {
			if (map.containsKey(AwsFacade.Key.AUCTION_START))
				auction.setAuction_start(df.parse(map.get(AwsFacade.Key.AUCTION_START)));
			if (map.containsKey(AwsFacade.Key.AUCTION_END))
				auction.setAuction_end(df.parse(map.get(AwsFacade.Key.AUCTION_END)));
		} catch (ParseException e) {
			log.error("Got bad date format from Amazon table: " + map.get(AwsFacade.Key.AUCTION_START) + 
					" or " + map.get(AwsFacade.Key.AUCTION_END), e);
		}
		
		if (map.containsKey(AwsFacade.Key.AUCTION_HIGHLIGHTED))
			auction.setAuction_highlighted(Boolean.parseBoolean(map.get(AwsFacade.Key.AUCTION_HIGHLIGHTED)));
		if (map.containsKey(AwsFacade.Key.AUCTION_MAXUNITS))
			auction.setAuction_maxunits(Integer.parseInt(map.get(AwsFacade.Key.AUCTION_MAXUNITS)));
		if (map.containsKey(AwsFacade.Key.AUCTION_STARTPRICE))
			auction.setAuction_startprice(Double.parseDouble(map.get(AwsFacade.Key.AUCTION_STARTPRICE)));
		if (map.containsKey(AwsFacade.Key.AUCTION_PRICECONFLICT))
			auction.setAuction_priceconflict(Double.parseDouble(map.get(AwsFacade.Key.AUCTION_PRICECONFLICT)));
		if (map.containsKey(AwsFacade.Key.AUCTION_SCHEDULE))
			auction.setAuction_schedule(ScheduleSerializer.toSchedule(map.get(AwsFacade.Key.AUCTION_SCHEDULE)));
		if (map.containsKey(AwsFacade.Key.MERCHANT_ID))
			auction.setMerchant_id(map.get(AwsFacade.Key.MERCHANT_ID));
		if (map.containsKey(AwsFacade.Key.MERCHANT_NAME))
			auction.setMerchant_name(map.get(AwsFacade.Key.MERCHANT_NAME));
		if (map.containsKey(AwsFacade.Key.MERCHANT_WEBSITE))
			auction.setMerchant_website(map.get(AwsFacade.Key.MERCHANT_WEBSITE));
		if (map.containsKey(AwsFacade.Key.AUCTION_DELETED))
			auction.setAuction_deleted(map.get(AwsFacade.Key.AUCTION_DELETED));
		if (map.containsKey(AwsFacade.Key.AUCTION_ENDED))
			auction.setAuction_ended(map.get(AwsFacade.Key.AUCTION_ENDED));
		if (map.containsKey(AwsFacade.Key.MERCHANT_FB_PAGE))
			auction.setMerchant_fb_page(map.get(AwsFacade.Key.MERCHANT_FB_PAGE));
		if (map.containsKey(AwsFacade.Key.REBATE_SENT))
			auction.setRebate_sent(map.get(AwsFacade.Key.REBATE_SENT));
		if (map.containsKey(AwsFacade.Key.AUCTION_CLEARED))
			auction.setAuction_cleared(map.get(AwsFacade.Key.AUCTION_CLEARED));
		if (map.containsKey(AwsFacade.Key.INVOICE_SENT))
			auction.setInvoice_sent(map.get(AwsFacade.Key.INVOICE_SENT));
		
		return auction;
	}
	
	
}
