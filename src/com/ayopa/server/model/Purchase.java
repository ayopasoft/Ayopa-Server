package com.ayopa.server.model;

import java.io.IOException;
import java.util.Date;

import com.ayopa.server.model.persistence.PurchasePersistence;

public class Purchase {

	private String purchase_id;
	private String purchase_auction_id;
	private String purchase_buyer_id;
	private int purchase_quantity;
	private double purchase_price;
	private Date auction_start;
	private Date auction_end;
	private Date purchase_date;
	private int purchase_rebate_sent;
	
	
	
	public int getPurchase_rebate_sent() {
		return purchase_rebate_sent;
	}

	public void setPurchase_rebate_sent(int purchase_rebate_sent) {
		this.purchase_rebate_sent = purchase_rebate_sent;
	}

	public Date getPurchase_date() {
		return purchase_date;
	}

	public void setPurchase_date(Date purchase_date) {
		this.purchase_date = purchase_date;
	}

	public String getPurchase_id() {
		return purchase_id;
	}
	
	public void setPurchase_id(String purchase_id) {
		this.purchase_id = purchase_id;
	}
	
	public int getPurchase_quantity() {
		return purchase_quantity;
	}
	
	public void setPurchase_quantity(int purchase_quantity) {
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
	
	public double getPurchase_price() {
		return purchase_price;
	}
	
	public void setPurchase_price(double d) {
		this.purchase_price = d;
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

	public static String putPurchase(String auction_id, String buyer_id, Integer quantity, Double price) throws IOException {
		
		PurchasePersistence pp = new PurchasePersistence();
		
		Purchase purchase = new Purchase();
		purchase.setPurchase_auction_id(auction_id);
		purchase.setPurchase_buyer_id(buyer_id);
		purchase.setPurchase_quantity(quantity);
		purchase.setPurchase_rebate_sent(0);
	
		Auction auction = new Auction();
		auction = auction.getAuction(auction_id);
				
		if (auction.getAuction_id() != null) {
			
			//CurrentAuction currAuction = new CurrentAuction();
			//int currQuantity = currAuction.getCurrentQuantity(auction_id);
			//currAuction = CurrentAuction.getCurrentAuctionInfo(auction, currQuantity);
			purchase.setPurchase_price(price);
			purchase.setAuction_start(auction.getAuction_start());
			purchase.setAuction_end(auction.getAuction_end());
		}	
		
		String purchaseReturn = pp.putPurchase(purchase);
		
		return purchaseReturn;
	}
	
}