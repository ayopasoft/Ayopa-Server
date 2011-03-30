package com.ayopa.server.model.persistence;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.ayopa.server.model.Merchant;
import com.ayopa.server.utils.AwsFacade;

public class MerchantPersistence {

	
	public String putMerchant (Merchant merchant) throws IOException {
		if (merchant.getMerchant_id() == null || merchant.getMerchant_id().length() == 0)
			merchant.setMerchant_id(UUID.randomUUID().toString());		
		
		Map<String, String> map = new HashMap<String, String>();
		map.put(AwsFacade.Key.MERCHANT_ID,merchant.getMerchant_id());
		map.put(AwsFacade.Key.MERCHANT_NAME, merchant.getMerchant_name());
		map.put(AwsFacade.Key.MERCHANT_USERNAME, merchant.getMerchant_username());
		map.put(AwsFacade.Key.MERCHANT_PASSWORD, merchant.getMerchant_password());
		map.put(AwsFacade.Key.MERCHANT_ADDRESS1, merchant.getMerchant_address1());
		map.put(AwsFacade.Key.MERCHANT_ADDRESS2, merchant.getMerchant_address2());
		map.put(AwsFacade.Key.MERCHANT_CITY, merchant.getMerchant_city());
		map.put(AwsFacade.Key.MERCHANT_STATE, merchant.getMerchant_state());
		map.put(AwsFacade.Key.MERCHANT_POSTALCODE, merchant.getMerchant_postalcode());
		map.put(AwsFacade.Key.MERCHANT_COUNTRY, merchant.getMerchant_country());
		map.put(AwsFacade.Key.MERCHANT_CONTACT, merchant.getMerchant_contact_name());
		map.put(AwsFacade.Key.MERCHANT_EMAIL, merchant.getMerchant_email());
		map.put(AwsFacade.Key.MERCHANT_WEBSITE, merchant.getMerchant_website());
		map.put(AwsFacade.Key.MERCHANT_FB_PAGE, merchant.getMerchant_fb_page_id());
		map.put(AwsFacade.Key.MERCHANT_AYOPA_FB, Boolean.toString(merchant.getMerchant_ayopa_fb_stream()));
				
		
		AwsFacade aws = AwsFacade.getInstance();
		aws.putRow(AwsFacade.Table.MERCHANT, merchant.getMerchant_id(), map);
		
		return merchant.getMerchant_id();
		
	}
	
	public Merchant getMerchant (String merchant_id) throws IOException {
		AwsFacade aws = AwsFacade.getInstance();
		
		Map<String,String> map = aws.getRow(AwsFacade.Table.MERCHANT, merchant_id);
		
		Merchant merchant = mapToMerchant(map);
		
		return merchant;
	}
	
	public Merchant mapToMerchant(Map<String, String> map) {
		Merchant merchant = new Merchant ();
		
		
		merchant.setMerchant_id(map.get(AwsFacade.Key.MERCHANT_ID));
		merchant.setMerchant_name(map.get(AwsFacade.Key.MERCHANT_NAME));
		merchant.setMerchant_username(map.get(AwsFacade.Key.MERCHANT_USERNAME));
		merchant.setMerchant_password(map.get(AwsFacade.Key.MERCHANT_PASSWORD));
		merchant.setMerchant_address1(map.get(AwsFacade.Key.MERCHANT_ADDRESS1));
		merchant.setMerchant_address2(map.get(AwsFacade.Key.MERCHANT_ADDRESS2));
		merchant.setMerchant_city(map.get(AwsFacade.Key.MERCHANT_CITY));
		merchant.setMerchant_state(map.get(AwsFacade.Key.MERCHANT_STATE));
		merchant.setMerchant_postalcode(map.get(AwsFacade.Key.MERCHANT_POSTALCODE));
		merchant.setMerchant_country(map.get(AwsFacade.Key.MERCHANT_COUNTRY));
		merchant.setMerchant_contact_name(map.get(AwsFacade.Key.MERCHANT_CONTACT));
		merchant.setMerchant_email(map.get(AwsFacade.Key.MERCHANT_EMAIL));
		merchant.setMerchant_website(map.get(AwsFacade.Key.MERCHANT_WEBSITE));
		merchant.setMerchant_fb_page_id(map.get(AwsFacade.Key.MERCHANT_FB_PAGE));
		merchant.setMerchant_ayopa_fb_stream(Boolean.parseBoolean(map.get(AwsFacade.Key.MERCHANT_AYOPA_FB)));
		
		
		return merchant;
	}
	
	
}
