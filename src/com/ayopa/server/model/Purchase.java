package com.ayopa.server.model;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ayopa.server.utils.AwsFacade;

public class Purchase {

	private String purchase_id;
	private String purchase_auction_id;
	private String purchase_buyer_id;
	private String purchase_quantity;
	private String purchase_price;
	
	public String getPurchase_id() {
		return purchase_id;
	}
	
	public void setPurchase_id(String purchase_id) {
		this.purchase_id = purchase_id;
	}
	
	public String getPurchase_quantity() {
		return purchase_quantity;
	}
	
	public void setPurchase_quantity(String purchase_quantity) {
		this.purchase_quantity = purchase_quantity;
	}
	
	public String getPurchase_auction_id() {
		return purchase_auction_id;
	}
	
	public void setPurchase_auction_id(String purchase_auction_id) {
		this.purchase_auction_id = purchase_auction_id;
	}
	
	public String getPurchase_buyer_id() {
		return purchase_buyer_id;
	}
	
	public void setPurchase_buyer_id(String purchase_buyer_id) {
		this.purchase_buyer_id = purchase_buyer_id;
	}
	
	public String getPurchase_price() {
		return purchase_price;
	}
	
	public void setPurchase_price(String purchase_price) {
		this.purchase_price = purchase_price;
	}
	
	public static String putPurchase(String auction_id, String buyer_id, String quantity) throws IOException {
		
		Purchase purchase = new Purchase();
		purchase.setPurchase_auction_id(auction_id);
		purchase.setPurchase_buyer_id(buyer_id);
		purchase.setPurchase_quantity(quantity);
		
		Auction auction = new Auction();
		auction = auction.getAuction(auction_id);
		
		if (auction.getAuction_id() != null) {
			
			//TODO: add calculated price to purchase
		}	
		
		return "foobar";
	}
	
	public int getCurrentQuantity(String auction_id) throws IOException{
		
		int quantity = 0;
		
		AwsFacade aws = AwsFacade.getInstance();
	
		String query = "select * from `" + AwsFacade.Table.PURCHASE + "` where `" 
		+ AwsFacade.Key.PURCHASE_AUCTION_ID + "` = '" + auction_id + "'";
		
		
		List<Map<String,String>> results = aws.selectRows(query);
		
		//sum quantity
		
		for (int i=0; i<results.size(); i++)
		{
			quantity += Integer.parseInt(results.get(i).get(AwsFacade.Key.PURCHASE_QUANTITY));	
		}
		
		return quantity;
	}
	
	public CurrentAuction getCurrentAuctionInfo(Auction auction, int quantity){
		
		List<ScheduleItem> schedule = auction.getAuction_schedule();
		
		double current_price = auction.getAuction_startprice();
		int current_level = 0;
		double next_price = auction.getAuction_startprice() - schedule.get(0).getDis();
		int next_level = schedule.get(0).getMin();
		double lowest_price = auction.getAuction_startprice() - schedule.get(schedule.size()-1).getDis();
		int lowest_level = schedule.get(schedule.size()-1).getMin();
		
		if (quantity != 0) {
			//where does that quantity fall in the schedule
			
			if (quantity >= schedule.get(0).getMin()) {  //check to see if quantity is above first minimum level
			
				double total_discount = 0.00;
				double current_discount = 0.00;
				double next_discount = 0.00;
				
				for (int i=0; i < schedule.size(); i++)
				{
					double dis = schedule.get(i).getDis();
					int min = schedule.get(i).getMin();
					int max = schedule.get(i).getMax();
					int add = schedule.get(i).getAdd();
					
						if (add != 0) {  //loop through add
							
							for (int j=0; j < ((max - min)/add) + 1; j++) {
								
								total_discount += (dis * j);
								
								if (quantity >= (min + (add * j)) && quantity < (min + (add * (j + 1)))) //quantity is in this level
								{	
									current_discount = total_discount;
									current_level = i;
								}
								else
								{	
									
									if (current_discount != 0 && next_discount == 0) {
									    next_discount = total_discount;
										next_level = min + (add * j);
									}	
								}
							}
						} 
						else {
							
							total_discount = dis;
							
							if (quantity >= min && quantity <= max){  //quantity is in this level
								current_discount = total_discount;
								current_level = i;
							}
							else
							{
								if (current_level != 0 && next_level == 0) {
								    next_discount = total_discount;
									next_level = min;
								}	
							}
							
						}
				}	
					
				current_price = auction.getAuction_startprice() - current_discount;
				next_price = auction.getAuction_startprice() - next_discount;
				lowest_price = auction.getAuction_startprice() - total_discount;
			}
		}
		
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
	
}
