package com.ayopa.server.model;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ayopa.server.model.persistence.AuctionPersistence;
import com.ayopa.server.model.persistence.PurchasePersistence;
import com.ayopa.server.utils.AwsFacade;

public class Auction {
	private static Log log = LogFactory.getLog(AuctionPersistence.class);
	
	public static class Key {
		
		public static final String AUCTION_ID = "auction_id";
		public static final String PRODUCT_ID = "product_id";
		public static final String PRODUCT_NAME = "product_name";
		public static final String PRODUCT_DESCR = "product_descr";
		public static final String PRODUCT_CAT = "product_category";
		public static final String PRODUCT_IMAGE_URL = "product_image";
		public static final String PRODUCT_URL = "product_link";
		public static final String AUCTION_START = "auction_start";
		public static final String AUCTION_END = "auction_end";
		public static final String AUCTION_HIGHLIGHTED = "auction_highlighted";
		public static final String AUCTION_MAXUNITS = "auction_maxunits";
		public static final String AUCTION_STARTPRICE = "auction_startprice";
		public static final String AUCTION_PRICECONFLICT = "pricing_conflict";
		public static final String AUCTION_SCHEDULE = "auction_schedule";
		public static final String MERCHANT_ID = "merchant_id";
		public static final String MERCHANT_NAME = "merchant_name";
		public static final String MERCHANT_WEBSITE = "merchant_website";
		public static final String SCHEDULE_ROW = "schedule_row";
		public static final String DISCOUNT = "dis";
		public static final String MINIMUM = "min";
		public static final String MAXIMUM = "max";
		public static final String ADDITIONAL = "add";
		public static final String AUCTION_ENDED = "auction_ended";
		public static final String AUCTION_DELETED = "auction_deleted";
		public static final String REBATE_SENT = "rebate_sent";
		public static final String AUCTION_CLEARED = "auction_cleared";
		public static final String INVOICE_SENT = "invoice_sent";
	}
	
	
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
	private String merchant_fb_page;
	private String auction_ended;
	private String auction_deleted;
	private String rebate_sent;
	private String auction_cleared;
	private String invoice_sent;
	
	
	public String getInvoice_sent() {
		return invoice_sent;
	}
	public void setInvoice_sent(String invoice_sent) {
		this.invoice_sent = invoice_sent;
	}
	public String getAuction_cleared() {
		return auction_cleared;
	}
	public void setAuction_cleared(String auction_cleared) {
		this.auction_cleared = auction_cleared;
	}
	public String getRebate_sent() {
		return rebate_sent;
	}
	public void setRebate_sent(String rebate_sent) {
		this.rebate_sent = rebate_sent;
	}
	public String getMerchant_fb_page() {
		return merchant_fb_page;
	}
	public void setMerchant_fb_page(String merchant_fb_page) {
		this.merchant_fb_page = merchant_fb_page;
	}
	public String getAuction_ended() {
		return auction_ended;
	}
	public void setAuction_ended(String auction_ended) {
		this.auction_ended = auction_ended;
	}
	public String getAuction_deleted() {
		return auction_deleted;
	}
	public void setAuction_deleted(String auction_deleted) {
		this.auction_deleted = auction_deleted;
	}
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
	
	public List<Auction> getCurrentAuctions () throws IOException{
		List<Auction> auctions = new ArrayList<Auction>();
		AwsFacade aws = AwsFacade.getInstance();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");
		df.setTimeZone(TimeZone.getTimeZone("US/Mountain"));
		
		String now = df.format(Calendar.getInstance(TimeZone.getTimeZone("US/Mountain"), Locale.US).getTime());
		
		String query = "select * from `" + AwsFacade.Table.AUCTION + "` where `" 
		+ AwsFacade.Key.AUCTION_START + "` <= '" + now + "' and `" 
		+ AwsFacade.Key.AUCTION_END + "` >= '" + now + "' and `"
		//+ AwsFacade.Key.MERCHANT_ID + "` != '1' and `"
		+ AwsFacade.Key.AUCTION_ENDED + "` != '1' and `"
		+ AwsFacade.Key.AUCTION_DELETED + "` != '1' order by `"
		+ AwsFacade.Key.AUCTION_START + "` desc";
		
		log.info(query);
		
		List<Map<String,String>> results = aws.selectRows(query);
		
		if (results.size() == 0) {
			//return auction;
		}
		else {
			for (int i = 0; i < results.size(); i++){
				Auction auction = new Auction();
				auction = auction.getAuction(results.get(i).get(AwsFacade.Key.AUCTION_ID));
				auctions.add(auction);
			}
			
			
		}
			return auctions;
	}
	
	public List<Auction> getCurrentAuctions(String category) throws IOException {
		List<Auction> auctions = new ArrayList<Auction>();
		AwsFacade aws = AwsFacade.getInstance();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");
		df.setTimeZone(TimeZone.getTimeZone("US/Mountain"));
		
		String now = df.format(Calendar.getInstance(TimeZone.getTimeZone("US/Mountain"), Locale.US).getTime());
		
		String query = "select * from `" + AwsFacade.Table.AUCTION + "` where `" 
		+ AwsFacade.Key.PRODUCT_CAT + "` = '" + category + "' and `"
		+ AwsFacade.Key.AUCTION_START + "` <= '" + now + "' and `" 
		+ AwsFacade.Key.AUCTION_END + "` >= '" + now + "' and `"
		+ AwsFacade.Key.AUCTION_ENDED + "` != '1' and `"
		+ AwsFacade.Key.AUCTION_DELETED + "` != '1' order by `"
		+ AwsFacade.Key.AUCTION_START + "` desc";
		
		log.info(query);
		
		List<Map<String,String>> results = aws.selectRows(query);
		
		if (results.size() == 0) {
			//return auction;
		}
		else {
			for (int i = 0; i < results.size(); i++){
				Auction auction = new Auction();
				auction = auction.getAuction(results.get(i).get(AwsFacade.Key.AUCTION_ID));
				auctions.add(auction);
			}
			
			
		}
			return auctions;
	}
	
	public static List<Auction> getAuctionsforRebate() throws IOException {
		List<Auction> auctions = new ArrayList<Auction>();
		AwsFacade aws = AwsFacade.getInstance();
		AuctionPersistence ap = new AuctionPersistence();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz"); 
		df.setTimeZone(TimeZone.getTimeZone("US/Mountain"));
		
		String now = df.format(Calendar.getInstance(TimeZone.getTimeZone("US/Mountain"), Locale.US).getTime());
		
		String query = "select * from `" + AwsFacade.Table.AUCTION + "` where `" 
		+ AwsFacade.Key.AUCTION_END + "` <= '" + now + "' and `"
		+ AwsFacade.Key.REBATE_SENT + "` != '1' and `"
		+ AwsFacade.Key.AUCTION_CLEARED + "` = '1' and `"
		+ AwsFacade.Key.MERCHANT_ID + "` != '' order by `"
		+ AwsFacade.Key.MERCHANT_ID + "` asc";
		
		log.info(query);
		
		List<Map<String,String>> results = aws.selectRows(query);
		
		if (results.size() == 0) {
			//return empty auction;
		}
		else {
			for (int i = 0; i < results.size(); i++){
				Auction auction = new Auction();
				auction = ap.mapToAuction(results.get(i));
				auctions.add(auction);
			}
			
			
		}
			return auctions;
	}
	
	public static List<Auction> getAuctionsforInvoice() throws IOException {
		List<Auction> auctions = new ArrayList<Auction>();
		AwsFacade aws = AwsFacade.getInstance();
		AuctionPersistence ap = new AuctionPersistence();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz"); 
		df.setTimeZone(TimeZone.getTimeZone("US/Mountain"));
		
		String now = df.format(Calendar.getInstance(TimeZone.getTimeZone("US/Mountain"), Locale.US).getTime());
		
		String query = "select * from `" + AwsFacade.Table.AUCTION + "` where `" 
		+ AwsFacade.Key.AUCTION_END + "` <= '" + now + "' and `"
		+ AwsFacade.Key.REBATE_SENT + "` != '1' and `"
		+ AwsFacade.Key.AUCTION_CLEARED + "` != '1' and `"
		+ AwsFacade.Key.INVOICE_SENT + "` != '1' and `"
		+ AwsFacade.Key.MERCHANT_ID + "` != '' order by `"
		+ AwsFacade.Key.MERCHANT_ID + "` asc";
		
		log.info(query);
		
		List<Map<String,String>> results = aws.selectRows(query);
		
		if (results.size() == 0) {
			//return empty auction;
		}
		else {
			for (int i = 0; i < results.size(); i++){
				Auction auction = new Auction();
				auction = ap.mapToAuction(results.get(i));
				auctions.add(auction);
			}
			
			
		}
			return auctions;
	}
	
	public List<String> getCurrentCategories() throws IOException {
		List<String> categories = new ArrayList<String>();
		AwsFacade aws = AwsFacade.getInstance();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");
		df.setTimeZone(TimeZone.getTimeZone("US/Mountain"));
		
		String now = df.format(Calendar.getInstance(TimeZone.getTimeZone("US/Mountain"), Locale.US).getTime());
		
		String query = "select `" + AwsFacade.Key.PRODUCT_CAT + "` from `" + AwsFacade.Table.AUCTION + "` where `" 
		+ AwsFacade.Key.AUCTION_START + "` <= '" + now + "' and `" 
		+ AwsFacade.Key.AUCTION_END + "` >= '" + now + "' and `"
		+ AwsFacade.Key.AUCTION_ENDED + "` != '1' and `"
		+ AwsFacade.Key.AUCTION_DELETED + "` != '1' order by `"
		+ AwsFacade.Key.AUCTION_START + "` desc";
		
		log.info(query);
		
		List<Map<String,String>> results = aws.selectRows(query);
		
		
			for (int i = 0; i < results.size(); i++){
				if (!categories.contains(results.get(i).get(AwsFacade.Key.PRODUCT_CAT)))
					categories.add(results.get(i).get(AwsFacade.Key.PRODUCT_CAT));
			}
			
			Collections.sort(categories);
			
			return categories;
	}
	
	public Auction getAuctionForProduct (String merchant_id, String product_id) throws IOException{
		Auction auction = new Auction();
		AuctionPersistence ap = new AuctionPersistence();
		
		AwsFacade aws = AwsFacade.getInstance();
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");
		df.setTimeZone(TimeZone.getTimeZone("US/Mountain"));
		
		String now = df.format(Calendar.getInstance(TimeZone.getTimeZone("US/Mountain"), Locale.US).getTime());
	
	
		String query = "select * from `" + AwsFacade.Table.AUCTION + "` where `" 
		+ AwsFacade.Key.MERCHANT_ID + "` = '" + merchant_id + "' and `" 
		+ AwsFacade.Key.PRODUCT_ID + "` = '" + product_id + "' and `" 
		+ AwsFacade.Key.AUCTION_START + "` <= '" + now + "' and `" 
		+ AwsFacade.Key.AUCTION_END + "` >= '" + now + "' and `"
		+ AwsFacade.Key.AUCTION_ENDED + "` != '1' and `"
		+ AwsFacade.Key.AUCTION_DELETED + "` != '1' order by `"
		+ AwsFacade.Key.AUCTION_START + "` desc";
		
		log.info(query);
		
		List<Map<String,String>> results = aws.selectRows(query);
		
		
		if (results.size() == 0) {
			//return auction;
		}
		else {
			if (results.size() > 1){
				log.info("getAuctionForProduct returned more than 1 result");
			}
			
			auction = ap.mapToAuction(results.get(0));
			
		}
		
			return auction;
			
	}

	public List<AuctionDTO> getHighlightedAuctionsForFBPage (String page_id) throws IOException{
		
		
		List<AuctionDTO> auctions = new ArrayList<AuctionDTO>();
		
		AwsFacade aws = AwsFacade.getInstance();
		AuctionPersistence ap = new AuctionPersistence();
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz"); 
		df.setTimeZone(TimeZone.getTimeZone("US/Mountain"));
		
		String now = df.format(Calendar.getInstance(TimeZone.getTimeZone("US/Mountain"), Locale.US).getTime());
	
		String query = "select * from `" + AwsFacade.Table.AUCTION + "` where `" 
		+ AwsFacade.Key.MERCHANT_FB_PAGE + "` = '" + page_id + "' and `" 
		+ AwsFacade.Key.AUCTION_START + "` <= '" + now + "' and `" 
		+ AwsFacade.Key.AUCTION_END + "` >= '" + now + "' and `"
		+ AwsFacade.Key.AUCTION_ENDED + "` != '1' and `"
		+ AwsFacade.Key.AUCTION_HIGHLIGHTED + "` = 'true' and `"
		+ AwsFacade.Key.AUCTION_DELETED + "` != '1' order by `"
		+ AwsFacade.Key.AUCTION_START + "` desc";
		
		log.info(query);
		
		List<Map<String,String>> results = aws.selectRows(query);
		
		if (results.size() == 0) {
			//return auction;
		}
		else {
			for (int i = 0; i < results.size(); i++){
				Auction auction = new Auction();
				AuctionDTO auctionDTO = new AuctionDTO();
				
				auction = ap.mapToAuction(results.get(i));
				auctionDTO = AuctionDTO.auctionToAuctionDTO(auction);
				auctions.add(auctionDTO);
			}
			
			
		}
			return auctions;
			
	}
	
 public List<AuctionDTO> getAuctionsForFBPage (String page_id) throws IOException{
		
		
		List<AuctionDTO> auctions = new ArrayList<AuctionDTO>();
		
		AwsFacade aws = AwsFacade.getInstance();
		AuctionPersistence ap = new AuctionPersistence();
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");
		df.setTimeZone(TimeZone.getTimeZone("US/Mountain"));
		
		String now = df.format(Calendar.getInstance(TimeZone.getTimeZone("US/Mountain"), Locale.US).getTime());
	
		String query = "select * from `" + AwsFacade.Table.AUCTION + "` where `" 
		+ AwsFacade.Key.MERCHANT_FB_PAGE + "` = '" + page_id + "' and `" 
		+ AwsFacade.Key.AUCTION_START + "` <= '" + now + "' and `" 
		+ AwsFacade.Key.AUCTION_END + "` >= '" + now + "' and `"
		+ AwsFacade.Key.AUCTION_ENDED + "` != '1' and `"
		+ AwsFacade.Key.AUCTION_HIGHLIGHTED + "` != 'true' and `"
		+ AwsFacade.Key.AUCTION_DELETED + "` != '1' order by `"
		+ AwsFacade.Key.AUCTION_START + "` desc";
		
		log.info(query);
		
		List<Map<String,String>> results = aws.selectRows(query);
		
		if (results.size() == 0) {
			//return auction;
		}
		else {
			for (int i = 0; i < results.size(); i++){
				Auction auction = new Auction();
				AuctionDTO auctionDTO = new AuctionDTO();
				
				auction = ap.mapToAuction(results.get(i));
				auctionDTO = AuctionDTO.auctionToAuctionDTO(auction);
				auctions.add(auctionDTO);
			}
			
			
		}
			return auctions;
			
	}
 
   public static List<Purchase> getPurchasesForAuction (String auction_id) throws IOException{
	   List<Purchase> purchases = new ArrayList<Purchase>();
	   AwsFacade aws = AwsFacade.getInstance();
	   PurchasePersistence pp = new PurchasePersistence();
	   
	   String query = "select * from `" + AwsFacade.Table.PURCHASE + "` where `" 
		+ AwsFacade.Key.PURCHASE_AUCTION_ID + "` = '" + auction_id + "' and `" 
		+ AwsFacade.Key.PURCHASE_BUYER_ID + "` != '' order by `"
		+ AwsFacade.Key.PURCHASE_BUYER_ID + "` asc";
	   
	   List<Map<String,String>> results = aws.selectRows(query);
		
		if (results.size() == 0) {
			//return empty auction;
		}
		else {
			for (int i = 0; i < results.size(); i++){
				Purchase purchase = new Purchase();
				purchase = pp.mapToPurchase(results.get(i));
				purchases.add(purchase);
			}
			
			
		}
			return purchases;
	   
   }
	
	public List<AuctionDTO> getAuctionsForBuyer (String buyer_id) throws IOException{
		
		
		List<AuctionDTO> auctions = new ArrayList<AuctionDTO>();
		
		AwsFacade aws = AwsFacade.getInstance();
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz"); 
		df.setTimeZone(TimeZone.getTimeZone("US/Mountain"));
		
		String now = df.format(Calendar.getInstance(TimeZone.getTimeZone("US/Mountain"), Locale.US).getTime());
	
		String query = "select * from `" + AwsFacade.Table.PURCHASE + "` where `" 
		+ AwsFacade.Key.PURCHASE_BUYER_ID + "` = '" + buyer_id + "' and `" 
		+ AwsFacade.Key.PURCHASE_DATE + "` != '' and `" 
		+ AwsFacade.Key.AUCTION_START + "` <= '" + now + "' and `" 
		+ AwsFacade.Key.AUCTION_END + "` >= '" + now + "' order by `"
		+ AwsFacade.Key.PURCHASE_DATE + "` desc";
		
		log.info(query);
		
		List<Map<String,String>> results = aws.selectRows(query);
		
		if (results.size() == 0) {
			//return auction;
		}
		else {
			for (int i = 0; i < results.size(); i++){
				Auction auction = new Auction();
				AuctionDTO auctionDTO = new AuctionDTO();
				auction = auction.getAuction(results.get(i).get(AwsFacade.Key.PURCHASE_AUCTION_ID));
				
				if (!auction.getAuction_ended().equals("1") && !auction.getAuction_deleted().equals("1")) {
					auctionDTO = AuctionDTO.auctionToAuctionDTO(auction);
					auctionDTO.setPurchase_price(Double.parseDouble(results.get(i).get(AwsFacade.Key.PURCHASE_PRICE)));
					auctionDTO.setRebate(auctionDTO.getPurchase_price() - auctionDTO.getCurrent_price());
					auctions.add(auctionDTO);
				}
				
				
			}
			
			
		}
			return auctions;
			
	}
	
public List<AuctionDTO> getAllAuctionsForBuyer (String buyer_id) throws IOException{
		
		
		List<AuctionDTO> auctions = new ArrayList<AuctionDTO>();
		
		AwsFacade aws = AwsFacade.getInstance();
		
		Date now = Calendar.getInstance(TimeZone.getTimeZone("US/Mountain"), Locale.US).getTime();
	
		String query = "select * from `" + AwsFacade.Table.PURCHASE + "` where `" 
		+ AwsFacade.Key.PURCHASE_BUYER_ID + "` = '" + buyer_id + "' and `"
		+ AwsFacade.Key.PURCHASE_DATE + "` != '' order by `"
		+ AwsFacade.Key.PURCHASE_DATE + "` desc";
		
		log.info(query);
		
		List<Map<String,String>> results = aws.selectRows(query);
		
		if (results.size() == 0) {
			//return auction;
		}
		else {
			for (int i = 0; i < results.size(); i++){
				Auction auction = new Auction();
				AuctionDTO auctionDTO = new AuctionDTO();
				
				auction = auction.getAuction(results.get(i).get(AwsFacade.Key.PURCHASE_AUCTION_ID));
				if (!auction.getAuction_deleted().equals("1")) {
					
					auctionDTO = AuctionDTO.auctionToAuctionDTO(auction);
					auctionDTO.setPurchase_price(Double.parseDouble(results.get(i).get(AwsFacade.Key.PURCHASE_PRICE)));
					auctionDTO.setRebate(auctionDTO.getPurchase_price() - auctionDTO.getCurrent_price());
					if (auction.getAuction_end().before(now)) {
						auctionDTO.setExpired(Boolean.TRUE);
					}
				}
				
				auctions.add(auctionDTO);
			}
			
			
		}
			return auctions;
			
	}
	
	public String endAuction(String auctionID) throws IOException{
		Auction auction = new Auction();
				
		auction = auction.getAuction(auctionID);
		auction.setAuction_ended("1");
		auction.setAuction_end(Calendar.getInstance(TimeZone.getTimeZone("US/Mountain"), Locale.US).getTime());
		
		String result = auction.putAuction(auction.auctionToJson(auction));
		
		return result;
		
	}

	public String putAuction(String auctionDef) throws IOException {
		Auction auction = new Auction();
		AuctionPersistence ap = new AuctionPersistence();
		
		auction = auction.jsonToAuction(auctionDef);		
		
		auction.setMerchant_fb_page(Merchant.getMerchantFBPage(auction.getMerchant_id()));
		
		String auctionReturn = ap.putAuction(auction);
		
		Map<String,String> mapReturn = new HashMap<String, String> ();
		mapReturn.put("auction_id", auctionReturn);
		
		JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON( mapReturn );
		
		return jsonObject.toString();
	}
	
	public String auctionToJson(Auction auction){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a"); 
		df.setTimeZone(TimeZone.getTimeZone("US/Mountain"));
		
		Map<String,Object> auctionMap = new HashMap<String, Object> ();
		Map<String, List<Map<String,Object>>> auction_schedule = new HashMap<String, List<Map<String,Object>>>();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Map<String,Object>> auction_container = new HashMap<String, Map<String,Object>>();
		
			auctionMap.put(Auction.Key.AUCTION_ID, auction.auction_id);
			auctionMap.put(Auction.Key.PRODUCT_ID, auction.product_id);
			auctionMap.put(Auction.Key.PRODUCT_NAME, auction.product_title);
			auctionMap.put(Auction.Key.PRODUCT_DESCR, auction.product_description);
			try {
				auctionMap.put(Auction.Key.AUCTION_START, df.format(auction.auction_start));
				auctionMap.put(Auction.Key.AUCTION_END, df.format(auction.auction_end));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.error("Auction start or auction end dates error: " + e);
			}
			auctionMap.put(Auction.Key.AUCTION_MAXUNITS, auction.auction_maxunits);
			auctionMap.put(Auction.Key.AUCTION_STARTPRICE, auction.auction_startprice);
			auctionMap.put(Auction.Key.AUCTION_HIGHLIGHTED, auction.auction_highlighted);
			auctionMap.put(Auction.Key.PRODUCT_IMAGE_URL, auction.product_image);
			auctionMap.put(Auction.Key.PRODUCT_URL, auction.product_url);
			auctionMap.put(Auction.Key.PRODUCT_CAT, auction.product_category);
			auctionMap.put(Auction.Key.AUCTION_PRICECONFLICT, auction.auction_priceconflict);
			auctionMap.put(Auction.Key.MERCHANT_ID, auction.merchant_id);
			auctionMap.put(Auction.Key.MERCHANT_NAME, auction.merchant_name);
			auctionMap.put(Auction.Key.MERCHANT_WEBSITE, auction.merchant_website);	
			auctionMap.put(Auction.Key.AUCTION_DELETED, auction.auction_deleted);
			auctionMap.put(Auction.Key.AUCTION_ENDED, auction.auction_ended);
			auctionMap.put(Auction.Key.REBATE_SENT, auction.rebate_sent);
			auctionMap.put(Auction.Key.AUCTION_CLEARED, auction.auction_cleared);
			
			try {
				for (int i=0; i < auction.auction_schedule.size(); i++) {
					Map<String,Object> schedule_row = new HashMap<String, Object>();
					schedule_row.put(Auction.Key.DISCOUNT, auction.auction_schedule.get(i).getDis());
					schedule_row.put(Auction.Key.MINIMUM, auction.auction_schedule.get(i).getMin());
					schedule_row.put(Auction.Key.MAXIMUM, auction.auction_schedule.get(i).getMax());
					schedule_row.put(Auction.Key.ADDITIONAL, auction.auction_schedule.get(i).getAdd());
					list.add(schedule_row);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.error("Auction schedule empty: " + e);
			}
			
			
			auction_schedule.put(Auction.Key.SCHEDULE_ROW, list);
			
			auctionMap.put(Auction.Key.AUCTION_SCHEDULE, auction_schedule);
		
		
		auction_container.put("auction", auctionMap);
		
		JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON( auction_container );
		
		return jsonObject.toString();
	}
	
	public Auction jsonToAuction(String json)
	{
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a");
		df.setTimeZone(TimeZone.getTimeZone("US/Mountain"));
		
		JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON( json ); 
		Auction auction = new Auction();
		
		if (json.length() != 0 && json != null){
			
			
			JSONObject jsonAuction = (JSONObject) jsonObject.get("auction");
						
			auction.setAuction_id(jsonAuction.getString(Auction.Key.AUCTION_ID));
			auction.setProduct_id(jsonAuction.getString(Auction.Key.PRODUCT_ID));
			auction.setProduct_title(jsonAuction.getString(Auction.Key.PRODUCT_NAME));
			auction.setProduct_description(jsonAuction.getString(Auction.Key.PRODUCT_DESCR));
			auction.setProduct_image(jsonAuction.getString(Auction.Key.PRODUCT_IMAGE_URL));
			auction.setProduct_category(jsonAuction.getString(Auction.Key.PRODUCT_CAT));
			auction.setProduct_url(jsonAuction.getString(Auction.Key.PRODUCT_URL));
			auction.setAuction_highlighted(jsonAuction.getBoolean(Auction.Key.AUCTION_HIGHLIGHTED));
			
			try {
				auction.setAuction_start(df.parse(jsonAuction.getString(Auction.Key.AUCTION_START)));
				auction.setAuction_end(df.parse(jsonAuction.getString(Auction.Key.AUCTION_END)));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				log.error("Auction start or Auction end is invalid: " + e);
			}
			
			auction.setAuction_maxunits(jsonAuction.getInt(Auction.Key.AUCTION_MAXUNITS));
			auction.setAuction_startprice(jsonAuction.getDouble(Auction.Key.AUCTION_STARTPRICE));
			auction.setAuction_priceconflict(jsonAuction.getDouble(Auction.Key.AUCTION_PRICECONFLICT));
			auction.setMerchant_id(jsonAuction.getString(Auction.Key.MERCHANT_ID));
			auction.setMerchant_name(jsonAuction.getString(Auction.Key.MERCHANT_NAME));
			auction.setMerchant_website(jsonAuction.getString(Auction.Key.MERCHANT_WEBSITE));
			auction.setAuction_ended(jsonAuction.getString(Auction.Key.AUCTION_ENDED));
			auction.setAuction_deleted(jsonAuction.getString(Auction.Key.AUCTION_DELETED));
			auction.setRebate_sent(jsonAuction.getString(Auction.Key.REBATE_SENT));
			auction.setAuction_cleared(jsonAuction.getString(Auction.Key.AUCTION_CLEARED));
			
			JSONObject jsonAuctionSched = (JSONObject) JSONSerializer.toJSON( jsonAuction.get(Auction.Key.AUCTION_SCHEDULE));
			JSONArray jsonSched = (JSONArray) JSONSerializer.toJSON( jsonAuctionSched.get(Auction.Key.SCHEDULE_ROW) ); 
			
			auction.setAuction_schedule(ScheduleSerializer.toSchedule(jsonSched.toString()));
			
			
		}
		return auction;
	}
	
	public boolean validateAuction(String auctionDef) throws Exception {
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a"); 
		
		JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON( auctionDef ); 
		
		if (auctionDef.length() != 0 && auctionDef != null){
			
			JSONObject jsonAuction = (JSONObject) jsonObject.get("auction");
			
			if (!jsonAuction.containsKey(Auction.Key.PRODUCT_ID))
				throw new Exception ("Product ID is missing");
			
			if (!jsonAuction.containsKey(Auction.Key.PRODUCT_NAME))
				throw new Exception ("Product Name is missing");
			
			if (!jsonAuction.containsKey(Auction.Key.PRODUCT_DESCR))
				throw new Exception ("Product Description is missing");
			
			if (!jsonAuction.containsKey(Auction.Key.PRODUCT_IMAGE_URL))
				throw new Exception ("Product Image is missing");
			
			if (!jsonAuction.containsKey(Auction.Key.PRODUCT_CAT))
				throw new Exception ("Product Category is missing");
			
			if (!jsonAuction.containsKey(Auction.Key.PRODUCT_URL))
				throw new Exception ("Product URL is missing");
			
			if (!jsonAuction.containsKey(Auction.Key.AUCTION_HIGHLIGHTED))
				throw new Exception ("Auction Highlighted is missing");
			
			if (!jsonAuction.containsKey(Auction.Key.AUCTION_START))
				throw new Exception ("Auction Start is missing");
			
			if (!jsonAuction.containsKey(Auction.Key.AUCTION_END))
				throw new Exception ("Auction End is missing");
			
			if (!jsonAuction.containsKey(Auction.Key.AUCTION_MAXUNITS))
				throw new Exception ("Auction Max Units is missing");
			
			if (!jsonAuction.containsKey(Auction.Key.AUCTION_PRICECONFLICT))
				throw new Exception ("Auction Price Conflict is missing");
			
			if (!jsonAuction.containsKey(Auction.Key.MERCHANT_ID))
				throw new Exception ("Merchant ID is missing");
			
			if (!jsonAuction.containsKey(Auction.Key.MERCHANT_NAME))
				throw new Exception ("Merchant Name is missing");
			
			if (!jsonAuction.containsKey(Auction.Key.MERCHANT_WEBSITE))
				throw new Exception ("Merchant Website is missing");
			
			if (!jsonAuction.containsKey(Auction.Key.AUCTION_ENDED))
				throw new Exception ("Auction Ended is missing");
			
			if (!jsonAuction.containsKey(Auction.Key.AUCTION_DELETED))
				throw new Exception ("Auction Deleted is missing");
			
			if (!jsonAuction.containsKey(Auction.Key.AUCTION_SCHEDULE))
				throw new Exception ("Auction Schedule is missing");
			
			if (jsonAuction.getString(Auction.Key.PRODUCT_ID).length() == 0)
					throw new Exception("Product ID cannot be blank");
			
			if (jsonAuction.getString(Auction.Key.PRODUCT_NAME).length() == 0)
				throw new Exception ("Product Name cannot be blank");
			
			if (jsonAuction.getString(Auction.Key.PRODUCT_DESCR).length() == 0)
				throw new Exception ("Product Description cannot be blank");
			
			if (jsonAuction.getString(Auction.Key.PRODUCT_IMAGE_URL).length() == 0)
				throw new Exception ("Product Image URL cannot be blank");
			
			if (jsonAuction.getString(Auction.Key.PRODUCT_CAT).length() == 0)
				throw new Exception ("Product Category cannot be blank");
			
			if (jsonAuction.getString(Auction.Key.PRODUCT_URL).length() == 0)
				throw new Exception ("Product URL cannot be blank");
			
			if (jsonAuction.getString(Auction.Key.AUCTION_START).length() == 0)
				throw new Exception ("Auction Start date cannot be blank");
			
			if (jsonAuction.getString(Auction.Key.AUCTION_END).length() == 0)
				throw new Exception ("Auction End date cannot be blank");
			
			if (jsonAuction.getString(Auction.Key.AUCTION_MAXUNITS).length() == 0)
				throw new Exception ("Auction Max Units cannot be blank");
			
			if (jsonAuction.getString(Auction.Key.AUCTION_STARTPRICE).length() == 0)
				throw new Exception ("Auction Start Price cannot be blank");
			
			if (jsonAuction.getString(Auction.Key.AUCTION_PRICECONFLICT).length() == 0)
				throw new Exception ("Product Description cannot be blank");
			
			if (jsonAuction.getString(Auction.Key.MERCHANT_ID).length() == 0)
				throw new Exception ("Merchant ID cannot be blank");
			
			if (jsonAuction.getString(Auction.Key.MERCHANT_NAME).length() == 0)
				throw new Exception ("Merchant Name cannot be blank");
			
			if (jsonAuction.getString(Auction.Key.MERCHANT_WEBSITE).length() == 0)
				throw new Exception ("Merchant Website cannot be blank");
			
			if (jsonAuction.getString(Auction.Key.AUCTION_SCHEDULE).length() == 0)
				throw new Exception ("Auction Schedule cannot be blank");
			
			
			try {
				int maxunits = Integer.parseInt(jsonAuction.getString(Auction.Key.AUCTION_MAXUNITS));
				if (maxunits <= 0)
					throw new Exception ("Max Units must be greater than 0");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new Exception ("Auction Max Units must be an integer");
			}
			
			try {
				double start_price = Double.parseDouble(jsonAuction.getString(Auction.Key.AUCTION_STARTPRICE));
				if (start_price <= 0)
					throw new Exception ("Start price must be greater than 0");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new Exception ("Start Price must be a number");
			}
			
			
			try {
				double price_conflict = Double.parseDouble(jsonAuction.getString(Auction.Key.AUCTION_PRICECONFLICT));
				if (price_conflict < 0)
					throw new Exception ("Price conflict must be greater than or equal to 0");
				
			} catch (Exception e) {
				
				throw new Exception ("Price conflict must be a double");
			}
			

			
			try {
				
				Date auction_start = df.parse(jsonAuction.getString(Auction.Key.AUCTION_START));
				try {
					
					Date auction_end = df.parse(jsonAuction.getString(Auction.Key.AUCTION_END));
					if (auction_end.before(auction_start) || auction_end.equals(auction_start))
						throw new Exception ("Auction end date must be after start date");
					
				} catch (Exception e) {
					
					throw new Exception ("Auction end date is invalid");
				}
				
			} catch (ParseException e) {
				
				throw new Exception ("Auction start date is invalid");
			}
				
			
			
			//loop through schedule
			try {
				JSONObject jsonAuctionSched = (JSONObject) JSONSerializer.toJSON( jsonAuction.get(Auction.Key.AUCTION_SCHEDULE));
				JSONArray jsonSched = (JSONArray) JSONSerializer.toJSON( jsonAuctionSched.get(Auction.Key.SCHEDULE_ROW) ); 
				List<ScheduleItem> schedule = new ArrayList<ScheduleItem>();
				
				schedule = ScheduleSerializer.toSchedule(jsonSched.toString());
				
				for (int i=0; i < schedule.size(); i++){
					if ( (i + 1) < schedule.size()){
						if (schedule.get(i).getMax() >= schedule.get(i + 1).getMin())
							throw new Exception ("Min and Max values between schedule levels cannot overlap");
						if (schedule.get(i).getMin() >= schedule.get(i + 1).getMin())
							throw new Exception ("Schedule range is not in order");
						if (schedule.get(i).getMax() >= schedule.get(i + 1).getMax())
							throw new Exception ("Schedule range is not in order");
					}
					
					if (schedule.get(i).getDis() <= 0 )
						throw new Exception ("Discount must be greater than 0");
					
					if (schedule.get(i).getMin() <= 0 )
						throw new Exception ("Min must be greater than 0");
					
					if (schedule.get(i).getMax() <= 0 )
						throw new Exception ("Max must be greater than 0");
					
				}
			} catch (Exception e) {
				throw new Exception ("Problem serializing auction schedule");
			}
			
			
			
		}
		
		
		return true;
	}
	
	
	public static void clearAuctions(List<Map<String, String>> auction_info) throws IOException {
		
		AuctionPersistence ap = new AuctionPersistence();
		
		for (int i=0; i < auction_info.size(); i++){
			ap.putAuctionCleared(auction_info.get(i).get("auction_id"));
		}
		
	}
	
    public static void invoiceAuctions(List<Map<String, String>> auction_info) throws IOException {
		
		for (int i=0; i < auction_info.size(); i++){
			AuctionPersistence.putAttribute(auction_info.get(i).get("auction_id"), AwsFacade.Key.INVOICE_SENT, "1");
		}
		
	}

	
	
	
	
}
