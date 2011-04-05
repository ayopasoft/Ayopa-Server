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

import com.ayopa.server.model.Purchase;
import com.ayopa.server.utils.AwsFacade;

public class PurchasePersistence {
	private static Log log = LogFactory.getLog(AuctionPersistence.class);
	
	public String putPurchase (Purchase purchase) throws IOException {
		if (purchase.getPurchase_id() == null || purchase.getPurchase_id().length() == 0)
			purchase.setPurchase_id(UUID.randomUUID().toString());		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
		
		Map<String, String> map = new HashMap<String, String>();
		map.put(AwsFacade.Key.PURCHASE_ID,purchase.getPurchase_id());
		map.put(AwsFacade.Key.PURCHASE_AUCTION_ID, purchase.getPurchase_auction_id());
		map.put(AwsFacade.Key.PURCHASE_BUYER_ID, purchase.getPurchase_buyer_id());
		map.put(AwsFacade.Key.PURCHASE_QUANTITY, Integer.toString(purchase.getPurchase_quantity()));
		map.put(AwsFacade.Key.PURCHASE_PRICE, Double.toString(purchase.getPurchase_price()));
		map.put(AwsFacade.Key.AUCTION_START, df.format(purchase.getAuction_start()));
		map.put(AwsFacade.Key.AUCTION_END, df.format(purchase.getAuction_end()));
		
		AwsFacade aws = AwsFacade.getInstance();
		aws.putRow(AwsFacade.Table.PURCHASE, purchase.getPurchase_id(), map);
		
		return purchase.getPurchase_id();
		
	}
	
	public Purchase getPurchase (String purchase_id) throws IOException {
		AwsFacade aws = AwsFacade.getInstance();
		
		Map<String,String> map = aws.getRow(AwsFacade.Table.PURCHASE, purchase_id);
		
		Purchase purchase = mapToPurchase(map);
		
		return purchase;
	}
	
	public Purchase mapToPurchase(Map<String, String> map)  {
		Purchase purchase = new Purchase ();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
		
		purchase.setPurchase_id(map.get(AwsFacade.Key.PURCHASE_ID));
		purchase.setPurchase_auction_id(map.get(AwsFacade.Key.PURCHASE_AUCTION_ID));
		purchase.setPurchase_buyer_id(map.get(AwsFacade.Key.PURCHASE_BUYER_ID));
		purchase.setPurchase_quantity(Integer.parseInt(map.get(AwsFacade.Key.PURCHASE_QUANTITY)));
		purchase.setPurchase_price(Double.parseDouble(map.get(AwsFacade.Key.PURCHASE_PRICE)));
		try {
			purchase.setAuction_start(df.parse(map.get(AwsFacade.Key.AUCTION_START)));
			purchase.setAuction_end(df.parse(map.get(AwsFacade.Key.AUCTION_END)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			log.error("Purchase auction start date or auction end date encountered bad date" + e);
		}
		
		return purchase;
	}
	
	
}
