package com.ayopa.server.model.persistence;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.ayopa.server.model.Buyer;
import com.ayopa.server.utils.AwsFacade;

public class BuyerPersistence {

	
	public String putBuyer (Buyer buyer) throws IOException {
		if (buyer.getBuyer_id() == null || buyer.getBuyer_id().length() == 0)
			buyer.setBuyer_id(UUID.randomUUID().toString());		
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put(AwsFacade.Key.BUYER_ID,buyer.getBuyer_id());
		map.put(AwsFacade.Key.BUYER_ACCESS_TOKEN, buyer.getBuyer_access_token());
		map.put(AwsFacade.Key.BUYER_NAME, buyer.getBuyer_name());
		map.put(AwsFacade.Key.BUYER_EMAIL, buyer.getBuyer_email());
		map.put(AwsFacade.Key.BUYER_ADDR1, buyer.getBuyer_address1());
		map.put(AwsFacade.Key.BUYER_ADDR2, buyer.getBuyer_address2());
		map.put(AwsFacade.Key.BUYER_CITY, buyer.getBuyer_city());
		map.put(AwsFacade.Key.BUYER_STATE, buyer.getBuyer_state());
		map.put(AwsFacade.Key.BUYER_ZIP, buyer.getBuyer_zip());
		map.put(AwsFacade.Key.BUYER_COUNTRY, buyer.getBuyer_country());
		
		
		AwsFacade aws = AwsFacade.getInstance();
		aws.putRow(AwsFacade.Table.BUYER, buyer.getBuyer_id(), map);
		
		return buyer.getBuyer_id();
		
	}
	
	public Buyer getBuyer (String buyer_id) throws IOException {
		AwsFacade aws = AwsFacade.getInstance();
		
		Map<String,String> map = aws.getRow(AwsFacade.Table.BUYER, buyer_id);
		
		Buyer buyer = mapToBuyer(map);
		
		return buyer;
	}
	
	public Buyer mapToBuyer(Map<String, String> map) {
		Buyer buyer = new Buyer ();
		
		if (map.containsKey(AwsFacade.Key.BUYER_ID)){
			buyer.setBuyer_id(map.get(AwsFacade.Key.BUYER_ID));
		}
		
		if (map.containsKey(AwsFacade.Key.BUYER_ACCESS_TOKEN)){
			buyer.setBuyer_access_token(map.get(AwsFacade.Key.BUYER_ACCESS_TOKEN));
		}
		
		if (map.containsKey(AwsFacade.Key.BUYER_NAME)){
			buyer.setBuyer_name(map.get(AwsFacade.Key.BUYER_NAME));
		}
		
		if (map.containsKey(AwsFacade.Key.BUYER_EMAIL)){
			buyer.setBuyer_email(map.get(AwsFacade.Key.BUYER_EMAIL));
		}
		
		if (map.containsKey(AwsFacade.Key.BUYER_ADDR1)){
			buyer.setBuyer_address1(map.get(AwsFacade.Key.BUYER_ADDR1));
		}
		
		if (map.containsKey(AwsFacade.Key.BUYER_ADDR2)){
			buyer.setBuyer_address2(map.get(AwsFacade.Key.BUYER_ADDR2));
		}
		
		if (map.containsKey(AwsFacade.Key.BUYER_CITY)){
			buyer.setBuyer_city(map.get(AwsFacade.Key.BUYER_CITY));
		}
		
		if (map.containsKey(AwsFacade.Key.BUYER_STATE)){
			buyer.setBuyer_state(map.get(AwsFacade.Key.BUYER_STATE));
		}
		
		if (map.containsKey(AwsFacade.Key.BUYER_ZIP)){
			buyer.setBuyer_zip(map.get(AwsFacade.Key.BUYER_ZIP));
		}
		
		if (map.containsKey(AwsFacade.Key.BUYER_COUNTRY)){
			buyer.setBuyer_country(map.get(AwsFacade.Key.BUYER_COUNTRY));
		}
			
		return buyer;
	}
	
	
}
