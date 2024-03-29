package com.ayopa.server.model;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import com.ayopa.server.utils.AwsFacade;

public class CurrentAuction {
	
	private String auction_id;
	private double current_price;
	private int current_level;
	private double next_price;
	private int next_level;
	private double lowest_price;
	private int lowest_level;
	private double rebate_total;
	private double commission_total;
	private double auction_total;
	
	
	
	public double getRebate_total() {
		return rebate_total;
	}
	public void setRebate_total(double rebate_total) {
		this.rebate_total = rebate_total;
	}
	public double getCommission_total() {
		return commission_total;
	}
	public void setCommission_total(double commission_total) {
		this.commission_total = commission_total;
	}
	public double getAuction_total() {
		return auction_total;
	}
	public void setAuction_total(double auction_total) {
		this.auction_total = auction_total;
	}
	public String getAuction_id() {
		return auction_id;
	}
	public void setAuction_id(String auction_id) {
		this.auction_id = auction_id;
	}
	public double getCurrent_price() {
		return current_price;
	}
	public void setCurrent_price(double current_price) {
		this.current_price = current_price;
	}
	public int getCurrent_level() {
		return current_level;
	}
	public void setCurrent_level(int current_level) {
		this.current_level = current_level;
	}
	public double getNext_price() {
		return next_price;
	}
	public void setNext_price(double next_price) {
		this.next_price = next_price;
	}
	public int getNext_level() {
		return next_level;
	}
	public void setNext_level(int next_level) {
		this.next_level = next_level;
	}
	public double getLowest_price() {
		return lowest_price;
	}
	public void setLowest_price(double lowest_price) {
		this.lowest_price = lowest_price;
	}
	public int getLowest_level() {
		return lowest_level;
	}
	public void setLowest_level(int lowest_level) {
		this.lowest_level = lowest_level;
	}
	
	public Map<String,Object> getCurrentQuantity(String auction_id) throws IOException{
		
		Map<String, Object> map = new HashMap<String,Object>();
		int quantity = 0;
		double purchase_total = 0;
		
		AwsFacade aws = AwsFacade.getInstance();
	
		String query = "select * from `" + AwsFacade.Table.PURCHASE + "` where `" 
		+ AwsFacade.Key.PURCHASE_AUCTION_ID + "` = '" + auction_id + "'";
		
		
		List<Map<String,String>> results = aws.selectRows(query);
		
		//sum quantity
		
		for (int i=0; i<results.size(); i++)
		{
			quantity += Integer.parseInt(results.get(i).get(AwsFacade.Key.PURCHASE_QUANTITY));
			purchase_total += Double.parseDouble(results.get(i).get(AwsFacade.Key.PURCHASE_PRICE)) * Integer.parseInt(results.get(i).get(AwsFacade.Key.PURCHASE_QUANTITY));
		}
		
		map.put("quantity", quantity);
		map.put("total", purchase_total);
		
		return map;
	}
	
	
	public static CurrentAuction getCurrentAuctionInfo(Auction auction, int quantity){
		
		List<ScheduleItem> schedule = auction.getAuction_schedule();
		
		double current_price = auction.getAuction_startprice();
		int current_level = 0;
		double next_price = auction.getAuction_startprice() - schedule.get(0).getDis();
		int next_level = schedule.get(0).getMin();
		double lowest_price = auction.getAuction_startprice() - schedule.get(schedule.size()-1).getDis();
		int lowest_level = schedule.get(schedule.size()-1).getMin();
		
		//if (quantity != 0) {
			//where does that quantity fall in the schedule
			
			//if (quantity >= schedule.get(0).getMin()) {  //check to see if quantity is above first minimum level
				
				
				double total_discount = 0.00;
				int total_level = 0;
				double current_discount = 0.00;
				double next_discount = 0.00;
				boolean current_found = false;
				
				if (quantity < schedule.get(0).getMin()){
					current_level = quantity;
					current_discount = 0;
					current_found = true;
				}
				
				double dis = 0.00;
				int min = 0;
				int max = 0;
				int add = 0;
				
				for (int i=0; i < schedule.size(); i++)
				{
					dis = schedule.get(i).getDis();
					min = schedule.get(i).getMin();
					max = schedule.get(i).getMax();
					add = schedule.get(i).getAdd();
					
										
						if (add != 0) {  //loop through add
							int add_level = 0;
							
							for (int j=0; j < ((max - min)/add) + 1; j++) {
								
								total_discount += (dis);
								add_level += add;
									
								if (quantity >= (min + (add * j)) && quantity <= (min + (add * (j + 1)))) //quantity is in this level
								{	
									current_discount = total_discount;
									current_level = quantity;
									current_found = true;
								}
								else
								{	
									
									if (current_found && next_discount == 0.0) {
									    next_discount = total_discount;
										next_level = min + (add * j);
									}	
								}
							}
							total_level += add_level;
						} 
						else {
							
							total_discount += dis;
							total_level = min;
							
							if (quantity >= min){  //quantity is in this level
								current_discount = total_discount;
								current_level = quantity;
								current_found = true;
							}
							else
							{
								if (current_found && next_discount == 0.0) {
								    next_discount = total_discount;
									next_level = min;
								}	
							}
							
						}
				}	
				
				lowest_level = total_level;
				
				if (lowest_level < schedule.get(schedule.size()-1).getMin())
					lowest_level = schedule.get(schedule.size()-1).getMin(); 
				
				if (current_found && next_discount == 0.0) {
				    next_discount = total_discount;
					next_level = min;
				}	
				
				
				if (quantity > schedule.get(schedule.size()-1).getMax()){
					current_price = auction.getAuction_startprice() - total_discount;
					current_level = quantity;
					next_price = auction.getAuction_startprice() - total_discount;
					next_level = schedule.get(schedule.size()-1).getMax();
					lowest_price = auction.getAuction_startprice() - total_discount;
				}
				else
				{
					current_price = auction.getAuction_startprice() - current_discount;
					next_price = auction.getAuction_startprice() - next_discount;
					lowest_price = auction.getAuction_startprice() - total_discount;
				}
			//}
			//else
			//{
			//	current_level = quantity;
			//}
		//}
		
		CurrentAuction currAuction = new CurrentAuction();
		
		currAuction.setAuction_id(auction.getAuction_id());
		currAuction.setCurrent_price(current_price);
		currAuction.setCurrent_level(current_level);
		currAuction.setNext_price(next_price);
		currAuction.setNext_level(next_level);
		currAuction.setLowest_level(lowest_level);
		currAuction.setLowest_price(lowest_price);
		
		return currAuction;
	}
	
	public static Map<String,Long> getAuctionTimeRemaining(Auction auction){
		
		//check if auction ended manually?
		
		Map<String,Long> map = new HashMap<String,Long>();
		
		Calendar calendar1 = Calendar.getInstance(TimeZone.getTimeZone("US/Mountain"), Locale.US);
		Calendar calendar2 = Calendar.getInstance(TimeZone.getTimeZone("US/Mountain"), Locale.US);
		calendar1.setTime(Calendar.getInstance(TimeZone.getTimeZone("US/Mountain"), Locale.US).getTime());
	    calendar2.setTime(auction.getAuction_end());
	    
	    long milliseconds1 = calendar1.getTimeInMillis();
	    long milliseconds2 = calendar2.getTimeInMillis();
	    long diff = milliseconds2 - milliseconds1;
	    long diffInSeconds = diff / 1000;
	    
	    long sec = (diffInSeconds >= 60 ? diffInSeconds % 60 : diffInSeconds);
	    long min = (diffInSeconds = (diffInSeconds / 60)) >= 60 ? diffInSeconds % 60 : diffInSeconds;
	    long hrs = (diffInSeconds = (diffInSeconds / 60)) >= 24 ? diffInSeconds % 24 : diffInSeconds;
	    long days = (diffInSeconds = (diffInSeconds / 24)) >= 30 ? diffInSeconds % 30 : diffInSeconds;

	    	    
	    map.put("seconds", sec);
	    map.put("minutes", min);
	    map.put("hours", hrs);
	    map.put("days", days);
	    
	    return map;
	
	}
	
	public static Map<String, Object> getCurrentAuctionRebate(String merchant_id, Double current_price,
			Double total, int quantity) throws IOException {
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		Merchant merchant = new Merchant();
		merchant = merchant.getMerchant(merchant_id);
		
		double rebate_total = total - (current_price * quantity);

	    double commission_total = rebate_total * (merchant.getMerchant_commission()/100);
		
		double auction_total = rebate_total + commission_total;
		
		map.put("rebate_total", rebate_total);
		map.put("commission_total", commission_total);
		map.put("auction_total", auction_total);
		
		return map;
	}
	
	
	
}
