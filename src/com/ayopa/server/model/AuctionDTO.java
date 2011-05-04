package com.ayopa.server.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuctionDTO {
	private String title;
	private String link;
	private String image;
	private double start_price;
	private double current_price;
	private double next_price;
	private double lowest_price;
	private int start_quant;
	private int current_quant;
	private int next_quant;
	private int highest_quant;
	private double purchase_price;
	private long time_seconds;
	private long time_minutes;
	private long time_hours;
	private long time_days;
	private double rebate;
	private boolean expired;
	private boolean rebate_sent;
    private String merchant_name;
    private String merchant_url;
    private double price_conflict;
    private String category;
	
    
    
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getPrice_conflict() {
		return price_conflict;
	}
	public void setPrice_conflict(double price_conflict) {
		this.price_conflict = price_conflict;
	}
	public String getMerchant_name() {
		return merchant_name;
	}
	public void setMerchant_name(String merchant_name) {
		this.merchant_name = merchant_name;
	}
	public String getMerchant_url() {
		return merchant_url;
	}
	public void setMerchant_url(String merchant_url) {
		this.merchant_url = merchant_url;
	}
	public boolean isRebate_sent() {
		return rebate_sent;
	}
	public void setRebate_sent(boolean rebate_sent) {
		this.rebate_sent = rebate_sent;
	}
	public boolean isExpired() {
		return expired;
	}
	public void setExpired(boolean expired) {
		this.expired = expired;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public double getStart_price() {
		return start_price;
	}
	public double getCurrent_price() {
		return current_price;
	}
	public double getNext_price() {
		return next_price;
	}
	public double getLowest_price() {
		return lowest_price;
	}
	public double getPurchase_price() {
		return purchase_price;
	}
	public void setStart_price(double start_price) {
		this.start_price = start_price;
	}
	public void setCurrent_price(double current_price) {
		this.current_price = current_price;
	}
	public void setNext_price(double next_price) {
		this.next_price = next_price;
	}
	public void setLowest_price(double lowest_price) {
		this.lowest_price = lowest_price;
	}
	public void setPurchase_price(double purchase_price) {
		this.purchase_price = purchase_price;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public int getStart_quant() {
		return start_quant;
	}
	public void setStart_quant(int start_quant) {
		this.start_quant = start_quant;
	}
	public int getCurrent_quant() {
		return current_quant;
	}
	public void setCurrent_quant(int current_quant) {
		this.current_quant = current_quant;
	}
	public int getNext_quant() {
		return next_quant;
	}
	public void setNext_quant(int next_quant) {
		this.next_quant = next_quant;
	}
	public int getHighest_quant() {
		return highest_quant;
	}
	public void setHighest_quant(int highest_quant) {
		this.highest_quant = highest_quant;
	}
	
	public long getTime_seconds() {
		return time_seconds;
	}
	public void setTime_seconds(long time_seconds) {
		this.time_seconds = time_seconds;
	}
	public long getTime_minutes() {
		return time_minutes;
	}
	public void setTime_minutes(long time_minutes) {
		this.time_minutes = time_minutes;
	}
	public long getTime_hours() {
		return time_hours;
	}
	public void setTime_hours(long time_hours) {
		this.time_hours = time_hours;
	}
	public long getTime_days() {
		return time_days;
	}
	public void setTime_days(long time_days) {
		this.time_days = time_days;
	}
	
	public double getRebate() {
		return rebate;
	}
	public void setRebate(double rebate) {
		this.rebate = rebate;
	}
	
	public static AuctionDTO auctionToAuctionDTO (Auction auction) throws IOException {
		Map<String, Object> map = new HashMap<String,Object>();
		AuctionDTO auctionDTO = new AuctionDTO();
		auctionDTO.setTitle(auction.getProduct_title());
		auctionDTO.setLink(auction.getProduct_url());
		auctionDTO.setImage(auction.getProduct_image());
		auctionDTO.setStart_price(auction.getAuction_startprice());
		
		CurrentAuction currAuction = new CurrentAuction();
		
		map = currAuction.getCurrentQuantity(auction.getAuction_id());
		int quantity = (Integer) map.get("quantity");
		
		currAuction = CurrentAuction.getCurrentAuctionInfo(auction, quantity);
		
		auctionDTO.setCurrent_price(currAuction.getCurrent_price());
		auctionDTO.setNext_price(currAuction.getNext_price());
		auctionDTO.setLowest_price(currAuction.getLowest_price());
		auctionDTO.setStart_quant(currAuction.getCurrent_level());
		auctionDTO.setCurrent_quant(currAuction.getCurrent_level());
		auctionDTO.setNext_quant(currAuction.getNext_level());
		auctionDTO.setHighest_quant(currAuction.getLowest_level());
		auctionDTO.setPurchase_price(0);  //need to get the purchase price
		Map<String,Long> time = CurrentAuction.getAuctionTimeRemaining(auction);
		auctionDTO.setTime_days(time.get("days"));
		auctionDTO.setTime_hours(time.get("hours"));
		auctionDTO.setTime_minutes(time.get("minutes"));
		auctionDTO.setTime_seconds(time.get("seconds"));
		auctionDTO.setMerchant_name(auction.getMerchant_name());
		auctionDTO.setMerchant_url(auction.getMerchant_website());
		auctionDTO.setPrice_conflict(auction.getAuction_priceconflict());
		auctionDTO.setCategory(auction.getProduct_category());
		
		return auctionDTO;
		
	}
	
	public static List<AuctionDTO> auctionsToAuctionDTO (List<Auction> auctions) throws IOException {
		
		List<AuctionDTO> dtoList = new ArrayList<AuctionDTO>();
		Map<String, Object> map = new HashMap<String,Object>();
		
		for (int i = 0; i < auctions.size(); i++) {
			AuctionDTO auctionDTO = new AuctionDTO();
			auctionDTO.setTitle(auctions.get(i).getProduct_title());
			auctionDTO.setLink(auctions.get(i).getProduct_url());
			auctionDTO.setImage(auctions.get(i).getProduct_image());
			auctionDTO.setStart_price(auctions.get(i).getAuction_startprice());
			
			CurrentAuction currAuction = new CurrentAuction();
			map = currAuction.getCurrentQuantity(auctions.get(i).getAuction_id());
			int quantity = (Integer) map.get("quantity");
			
			currAuction = CurrentAuction.getCurrentAuctionInfo(auctions.get(i), quantity);
			
			auctionDTO.setCurrent_price(currAuction.getCurrent_price());
			auctionDTO.setNext_price(currAuction.getNext_price());
			auctionDTO.setLowest_price(currAuction.getLowest_price());
			auctionDTO.setStart_quant(currAuction.getCurrent_level());
			auctionDTO.setCurrent_quant(currAuction.getCurrent_level());
			auctionDTO.setNext_quant(currAuction.getNext_level());
			auctionDTO.setHighest_quant(currAuction.getLowest_level());
			auctionDTO.setPurchase_price(0);  //need to get the purchase price
			Map<String,Long> time = CurrentAuction.getAuctionTimeRemaining(auctions.get(i));
			auctionDTO.setTime_days(time.get("days"));
			auctionDTO.setTime_hours(time.get("hours"));
			auctionDTO.setTime_minutes(time.get("minutes"));
			auctionDTO.setTime_seconds(time.get("seconds"));
			auctionDTO.setMerchant_name(auctions.get(i).getMerchant_name());
			auctionDTO.setMerchant_url(auctions.get(i).getMerchant_website());
			auctionDTO.setPrice_conflict(auctions.get(i).getAuction_priceconflict());
			auctionDTO.setCategory(auctions.get(i).getProduct_category());
			
			dtoList.add(auctionDTO);
		}
		
		return dtoList;
	}
	
	public static List<String> getCategories(List<AuctionDTO> auctions) {
		
		
		List<String> categories = new ArrayList<String>();
		
		for (int i =0; i < auctions.size(); i++){
			if (!categories.contains(auctions.get(i).getCategory())){
				categories.add(auctions.get(i).getCategory());
				
			}
		}
		
		Collections.sort(categories);
		
		return categories;
	}
	
	
	
}
