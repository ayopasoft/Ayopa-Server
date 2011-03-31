package com.ayopa.server.model.persistence;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.ayopa.server.model.Purchase;
import com.ayopa.server.utils.AwsFacade;

public class PurchasePersistence {

	
	public String putPurchase (Purchase purchase) throws IOException {
		if (purchase.getPurchase_id() == null || purchase.getPurchase_id().length() == 0)
			purchase.setPurchase_id(UUID.randomUUID().toString());		
		
		Map<String, String> map = new HashMap<String, String>();
		map.put(AwsFacade.Key.PURCHASE_ID,purchase.getPurchase_id());
		map.put(AwsFacade.Key.PURCHASE_AUCTION_ID, purchase.getPurchase_auction_id());
		map.put(AwsFacade.Key.PURCHASE_BUYER_ID, purchase.getPurchase_buyer_id());
		map.put(AwsFacade.Key.PURCHASE_QUANTITY, purchase.getPurchase_quantity());
		map.put(AwsFacade.Key.PURCHASE_PRICE, purchase.getPurchase_price());
		
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
	
	public Purchase mapToPurchase(Map<String, String> map) {
		Purchase purchase = new Purchase ();
		
		purchase.setPurchase_id(map.get(AwsFacade.Key.PURCHASE_ID));
		purchase.setPurchase_auction_id(map.get(AwsFacade.Key.PURCHASE_AUCTION_ID));
		purchase.setPurchase_buyer_id(map.get(AwsFacade.Key.PURCHASE_BUYER_ID));
		purchase.setPurchase_quantity(map.get(AwsFacade.Key.PURCHASE_QUANTITY));
		purchase.setPurchase_price(map.get(AwsFacade.Key.PURCHASE_PRICE));
		
		return purchase;
	}
	
	
}
