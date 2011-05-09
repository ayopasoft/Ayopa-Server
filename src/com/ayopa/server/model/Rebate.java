package com.ayopa.server.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.ayopa.server.model.persistence.AuctionPersistence;
import com.ayopa.server.model.persistence.BuyerPersistence;
import com.ayopa.server.model.persistence.PurchasePersistence;
import com.ayopa.server.utils.AwsFacade;
import com.ayopa.server.utils.PaypalUtils;
import com.restfb.util.StringUtils;


public class Rebate {
	
	private String rebate_id;
	private double total;
	private String recipient;
	private List<String> items;

	
	
	public String getRebate_id() {
		return rebate_id;
	}

	public void setRebate_id(String rebate_id) {
		this.rebate_id = rebate_id;
	}

	public List<String> getItems() {
		return items;
	}

	public void setItems(List<String> items) {
		this.items = items;
	}



	public double getTotal() {
		return total;
	}



	public void setTotal(double total) {
		this.total = total;
	}



	public String getRecipient() {
		return recipient;
	}



	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}



	public static List<Rebate> getRebatesforConsumers(List<Auction> auctions) throws IOException
	{
		
		List<Purchase> purchases = new ArrayList<Purchase>();
		List<Rebate> rebates = new ArrayList<Rebate>();
		
		
		Rebate rebate = new Rebate();
		
		
		for (int i = 0; i < auctions.size(); i++) {
			purchases.addAll(Auction.getPurchasesForAuction(auctions.get(i).getAuction_id()));
		}
		
		Collections.sort(purchases, Purchase.BUYER_ID);
		
		String buyer_id = "0";
		double total = 0;
		List<String> items = new ArrayList<String>();
		
		for (int i = 0; i < purchases.size(); i++)
		{
			Map<String,Object> map = new HashMap<String,Object>();
			CurrentAuction currAuction = new CurrentAuction();
			map = currAuction.getCurrentQuantity(purchases.get(i).getPurchase_auction_id());
			int quantity = (Integer) map.get("quantity");
			Auction auction = new Auction();
			auction = auction.getAuction(purchases.get(i).getPurchase_auction_id());
			currAuction = CurrentAuction.getCurrentAuctionInfo(auction, quantity);
			Buyer buyer = new Buyer();
			BuyerPersistence bp = new BuyerPersistence();
			
			if (purchases.get(i).getPurchase_buyer_id().equals(buyer_id)){
				total += (purchases.get(i).getPurchase_price() - currAuction.getCurrent_price()) * purchases.get(i).getPurchase_quantity();
			}
			else
			{
				rebate = new Rebate();
				items = new ArrayList<String>();
				total = (purchases.get(i).getPurchase_price() - currAuction.getCurrent_price()) * purchases.get(i).getPurchase_quantity();
			}
			
			rebate.setTotal(total);
			items.add(auction.getProduct_title());
			rebate.setItems(items);
			rebate.setRebate_id(UUID.randomUUID().toString());
			buyer = bp.getBuyer(purchases.get(i).getPurchase_buyer_id());
			
			if (buyer.getBuyer_paypal() != null && !buyer.getBuyer_paypal().equals(""))
				rebate.setRecipient(buyer.getBuyer_paypal());
			else
				rebate.setRecipient(buyer.getBuyer_email());
			
			buyer_id = purchases.get(i).getPurchase_buyer_id();
			
			if ((i + 1) == purchases.size()){
				//last iteration - save rebate
				rebates.add(rebate);
			}
			else
			{
				if (!buyer_id.equals(purchases.get(i + 1).getPurchase_buyer_id())){ //finished with buyer - save rebate
					rebates.add(rebate);
					
				}
			}
		}
		
		return rebates;
	}
	
	public static String sendRebates() throws IOException
	{
		List<Rebate> rebates = new ArrayList<Rebate>();
		List<Auction> auctions = new ArrayList<Auction>();
		List<Purchase> purchases = new ArrayList<Purchase>();

		
		auctions = Auction.getAuctionsforRebate();
		rebates = Rebate.getRebatesforConsumers(auctions);
		
		List<String> receiverEmail = new ArrayList<String>();
		List<String> uniqueId = new ArrayList<String>();
		List<String> amount = new ArrayList<String>();
		List<String> note = new ArrayList<String>();
		String response = "";
		
		for (int i = 0; i < rebates.size(); i++)
		{
			receiverEmail.add(rebates.get(i).getRecipient());
			amount.add(Double.toString(rebates.get(i).getTotal()));
			uniqueId.add("");
			note.add("Rebate for: " + StringUtils.join(rebates.get(i).getItems()));
			
			if (i % 250 == 0 && rebates.size() >= 250){  //Paypal masspay can only process 250 items at a time
				response = PaypalUtils.massPayCode("Ayopa Rebate", "EmailAddress", receiverEmail, uniqueId, amount, note);
				
				if (response.equals("Success")){
					// set rebate sent on auctions
					for (int j = 0; j < auctions.size(); j++) {
						AuctionPersistence.putAttribute(auctions.get(j).getAuction_id(), AwsFacade.Key.REBATE_SENT, "1");
						purchases.addAll(Auction.getPurchasesForAuction(auctions.get(j).getAuction_id()));
					}
					
					// set rebate sent on purchases
					for (int j = 0; j < purchases.size(); j++){
						PurchasePersistence.putAttribute(purchases.get(j).getPurchase_id(), AwsFacade.Key.REBATE_SENT, "1");
					}
				}
				
				receiverEmail = new ArrayList<String>();
				amount = new ArrayList<String>();
				uniqueId = new ArrayList<String>();
				note = new ArrayList<String>();
				
			}
			
		}
		
		response = PaypalUtils.massPayCode("Ayopa Rebate", "EmailAddress", receiverEmail, uniqueId, amount, note);
		
		if (response.equals("Success")){
			// set rebate sent on auctions
			for (int j = 0; j < auctions.size(); j++) {
				AuctionPersistence.putAttribute(auctions.get(j).getAuction_id(), AwsFacade.Key.REBATE_SENT, "1");
				purchases.addAll(Auction.getPurchasesForAuction(auctions.get(j).getAuction_id()));
			}
			
			// set rebate sent on purchases
			for (int j = 0; j < purchases.size(); j++){
				PurchasePersistence.putAttribute(purchases.get(j).getPurchase_id(), AwsFacade.Key.REBATE_SENT, "1");
			}
		}
		
		return response;
	}

}
